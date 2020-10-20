package com.example.pdf;

import com.itextpdf.text.pdf.PdfReader;
import com.itextpdf.text.pdf.parser.ImageRenderInfo;
import com.itextpdf.text.pdf.parser.PdfReaderContentParser;
import com.itextpdf.text.pdf.parser.RenderListener;
import com.itextpdf.text.pdf.parser.TextRenderInfo;
import org.apache.log4j.Logger;
import org.apache.pdfbox.cos.COSName;
import org.apache.pdfbox.cos.COSStream;
import org.apache.pdfbox.pdfviewer.PageDrawer;
import org.apache.pdfbox.pdmodel.PDDocument;
import org.apache.pdfbox.pdmodel.PDPage;
import org.apache.pdfbox.pdmodel.PDResources;
import org.apache.pdfbox.pdmodel.common.PDRectangle;
import org.apache.pdfbox.util.Matrix;
import org.apache.pdfbox.util.PDFTextStripper;
import org.apache.pdfbox.util.PDFTextStripperByArea;
import org.apache.pdfbox.util.TextPosition;

import java.awt.*;
import java.awt.geom.AffineTransform;
import java.awt.geom.GeneralPath;
import java.awt.geom.PathIterator;
import java.awt.geom.Rectangle2D;
import java.io.File;
import java.io.IOException;
import java.util.List;
import java.util.*;

public class PdfResolver {
    private final Logger log = Logger.getLogger(this.getClass());
    public static final String SPLITTER = "$$";
    private PDDocument document;
    private String filePath;
    public PdfResolver(String filePath) throws IOException {
        this.filePath = filePath;
        this.document = PDDocument.load(new File(filePath));
    }

    public void close() {
        try {
            document.close();
        } catch (IOException e) {
            log.error(e);
        }
    }

    public String resolvePage(int page,boolean removeBlank) throws IOException {
        return resolvePage(page,page,removeBlank);
    }

    private String resolvePage(int startPage, int endPage, boolean removeBlank) throws IOException {
        PDFTextStripper stripper = new PDFTextStripper();
        // 设置按顺序输出
        stripper.setSortByPosition(true);
        stripper.setStartPage(startPage);
        stripper.setEndPage(endPage);
        String pageContent = stripper.getText(document);
        return removeBlank ? removeBlank(pageContent) : pageContent;
    }

    public String resolvePage(int page) throws IOException {
        return resolvePage(page,page);
    }

    public String resolvePage(int startPage, int endPage) throws IOException {
        return resolvePage(startPage,endPage,true);
    }

    public String resolveRegionOfPage(Rectangle2D.Float region, int page) throws IOException {
        PDFTextStripperByArea textStripper = new PDFTextStripperByArea();
        textStripper.setSortByPosition(true);
        textStripper.addRegion("region", region);
        List<PDPage> allPages = document.getDocumentCatalog().getAllPages();
        PDPage docPage = allPages.get(page - 1);
        textStripper.extractRegions(docPage);
        return removeBlank(textStripper.getTextForRegion("region"));
    }

    public List<String> resolveTable(int page,int colCount) throws Exception {
        List<String> result = resolveTable(page);
        Iterator<String> itr = result.iterator();
        while (itr.hasNext()) {
            String rowContent = itr.next();
            if(countContains(rowContent,SPLITTER) != colCount -1){
                itr.remove();
            }
        }
        return result;
    }

    public List<String> resolveTable(int page) throws Exception {
        List<PDPage> allPages = document.getDocumentCatalog().getAllPages();

        CoordinatePageDrawer pageDrawer = new CoordinatePageDrawer();
        PDPage docPage = allPages.get(page - 1);
        pageDrawer.processStream(docPage, docPage.findResources(), docPage.getContents().getStream());

        return judgeCoordinate(pageDrawer.getCoordinates(), page);
    }

    private String removeBlank(String content) {
        StringBuilder builder = new StringBuilder();
        char currentChar;
        for (int i = 0; i < content.length(); i++) {
            currentChar = content.charAt(i);
            if (currentChar == 32 || currentChar == 160 || currentChar == 13 || currentChar == 10) {
                continue;
            }
            builder.append(currentChar);
        }
        return builder.toString();
    }

    // 定义返回页码
    private int i = 0;

    public int getPageOfKeyWords(String ... keywords){
        int result = 0;
        try {
            PdfReader pdfReader = new PdfReader(filePath);
            int pageNum = pdfReader.getNumberOfPages();
            PdfReaderContentParser pdfReaderContentParser = new PdfReaderContentParser(
                    pdfReader);

            // 下标从1开始
            for (i = 1; i < pageNum; i++) {
                StringBuilder fullPageText = new StringBuilder();
                pdfReaderContentParser.processContent(i, new CustomRenderListener() {
                    @Override
                    public void renderText(TextRenderInfo textRenderInfo) {
                        fullPageText.append(textRenderInfo.getText());
                    }
                });
                boolean contains = true;
                for(String keyword : keywords){
                    if(!fullPageText.toString().contains(keyword)){
                        contains = false;
                        break;
                    }
                }

                if(contains){
                    result = i;
                    break;
                }
            }
        } catch (IOException e) {
            log.error(e);
        }
        return result;
    }
    private List<String> judgeCoordinate(List<Coordinate> coordinates, int page) throws Exception {
        removeDumplicated(coordinates);

        sortByY(coordinates);

        List<List<Coordinate>> eachRowCoordinates = seperateRows(coordinates);

        sortByX(eachRowCoordinates);

        return resolveRows(eachRowCoordinates,page);
    }

    private List<String> resolveRows(List<List<Coordinate>> eachRowCoordinates, int page) throws IOException {
        List<String> results = new ArrayList<>();
        for(int i=0;i<eachRowCoordinates.size()-1;i++){
            List<Coordinate> coordinatesOfOneRow = eachRowCoordinates.get(i);
            StringJoiner sj = new StringJoiner(SPLITTER);
            for(int j=0;j<coordinatesOfOneRow.size()-1;j++){
                Coordinate preCoordinate = coordinatesOfOneRow.get(j);
                Coordinate nextCoordinate = coordinatesOfOneRow.get(j+1);
                Coordinate newRowCoordinate = eachRowCoordinates.get(i+1).get(0);

                String info = resolveRegionOfPage(new Rectangle2D.Float(preCoordinate.getX(), preCoordinate.getY(),
                        nextCoordinate.getX() - preCoordinate.getX(),
                        newRowCoordinate.getY() - preCoordinate.getY()),page);
                sj.add(info.replaceAll("\r|\n", ""));
            }
            results.add(sj.toString());
        }
        return results;
    }

    private void sortByX(List<List<Coordinate>> eachRowCoordinates) {
        //每一行都按X升序排序
        for(List<Coordinate> coordinatesOfOneRow : eachRowCoordinates){
            Collections.sort(coordinatesOfOneRow, new Comparator<Coordinate>() {
                @Override
                public int compare(Coordinate o1, Coordinate o2) {
                    return (Math.abs(o1.getX()-o2.getX())<=2) ? 0 : o1.getX()-o2.getX();
                }
            });
        }
    }

    private List<List<Coordinate>> seperateRows(List<Coordinate> coordinates) {
        List<List<Coordinate>> eachRowCoordinates = new ArrayList<>();

        int rowIndex = 0;

        for(int i=0;i<coordinates.size()-1;i++){
            Coordinate preCoordinate = coordinates.get(i);
            Coordinate nextCoordinate = coordinates.get(i+1);

            if(i == 0){
                List<Coordinate> firstRowCoordinates = new ArrayList<>();
                firstRowCoordinates.add(preCoordinate);
                eachRowCoordinates.add(firstRowCoordinates);
            }

            if(Math.abs(preCoordinate.getY()-nextCoordinate.getY())<=2){
                eachRowCoordinates.get(rowIndex).add(nextCoordinate);
            }else{
                List<Coordinate> newRowCoordinates = new ArrayList<>();
                newRowCoordinates.add(nextCoordinate);
                eachRowCoordinates.add(newRowCoordinates);
                rowIndex++;
            }
        }
        return eachRowCoordinates;
    }

    private void sortByY(List<Coordinate> coordinates) {
        //按Y坐标升序排序
        Collections.sort(coordinates, new Comparator<Coordinate>() {
            @Override
            public int compare(Coordinate o1, Coordinate o2) {
                return (Math.abs(o1.getY()-o2.getY())<=2) ? 0 : o1.getY()-o2.getY();
            }
        });
    }

    private void removeDumplicated(List<Coordinate> coordinates) {
        for (int i = 0; i < coordinates.size() - 1; i++) {
            for (int j = coordinates.size() - 1; j > i; j--) {
                if (coordinates.get(j).equals(coordinates.get(i))) {
                    coordinates.remove(j);
                }
            }
        }
    }

    private int countContains(String content,String keyword){
        return (content.length() - content.replace(keyword,"").length())/keyword.length();
    }
    private class CoordinatePageDrawer extends PageDrawer {

        private final List<Coordinate> coordinates = new ArrayList<>();

        public List<Coordinate> getCoordinates() {
            return coordinates;
        }

        public CoordinatePageDrawer() throws IOException {
            super();
        }

        // ignore text
        @Override
        protected void processTextPosition(TextPosition text) {
        }

        // ignore bitmaps
        @Override
        public void drawImage(Image awtImage, AffineTransform at) {
        }

        // ignore shadings
        @Override
        public void shFill(COSName shadingName) throws IOException {
        }

        @Override
        public void processStream(PDPage aPage, PDResources resources, COSStream cosStream) throws IOException {
            PDRectangle cropBox = aPage.findCropBox();
            this.pageSize = cropBox.createDimension();
            super.processStream(aPage, resources, cosStream);
        }

        @Override
        public void fillPath(int windingRule) throws IOException {
            printPath();
            getLinePath().reset();

        }

        @Override
        public void strokePath() throws IOException {
            printPath();
            getLinePath().reset();
        }

        private void printPath() {
            GeneralPath path = getLinePath();
            PathIterator pathIterator = path.getPathIterator(null);
            double x = 0, y = 0;
            double coords[] = new double[6];
            while (!pathIterator.isDone()) {
                switch (pathIterator.currentSegment(coords)) {
                    case PathIterator.SEG_MOVETO:
                        x = coords[0];
                        y = coords[1];
                        Coordinate startCoordinate = new Coordinate((int) x, (int) y);
                        coordinates.add(startCoordinate);
                        break;
                    case PathIterator.SEG_LINETO:
                        double width = getEffectiveWidth(coords[0] - x, coords[1] - y);
                        x = coords[0];
                        y = coords[1];
                        break;
                    case PathIterator.SEG_QUADTO:
                        x = coords[2];
                        y = coords[3];
                        break;
                    case PathIterator.SEG_CUBICTO:
                        x = coords[4];
                        y = coords[5];
                        break;
                    case PathIterator.SEG_CLOSE:
                }

                pathIterator.next();
            }
        }

        double getEffectiveWidth(double dirX, double dirY) {
            if (dirX == 0 && dirY == 0)
                return 0;
            Matrix ctm = getGraphicsState().getCurrentTransformationMatrix();
            double widthX = dirY;
            double widthY = -dirX;
            double widthXTransformed = widthX * ctm.getValue(0, 0) + widthY * ctm.getValue(1, 0);
            double widthYTransformed = widthX * ctm.getValue(0, 1) + widthY * ctm.getValue(1, 1);
            double factor = Math.sqrt((widthXTransformed * widthXTransformed + widthYTransformed * widthYTransformed) / (widthX * widthX + widthY * widthY));
            return getGraphicsState().getLineWidth() * factor;
        }
    }

    class Coordinate {
        private int x;
        private int y;

        public Coordinate(int x, int y) {
            this.x = x;
            this.y = y;
        }

        public int getX() {
            return x;
        }

        public void setX(int x) {
            this.x = x;
        }

        public int getY() {
            return y;
        }

        public void setY(int y) {
            this.y = y;
        }

        @Override
        public String toString() {
            return "Coordinate{" +
                    "x=" + x +
                    ", y=" + y +
                    '}';
        }

        @Override
        public boolean equals(Object obj) {
            Coordinate coordinate = (Coordinate) obj;
            if(coordinate.getX() == this.x && Math.abs(coordinate.getY() - this.y) <= 2){
                return true;
            }
            if(coordinate.getY() == this.y && Math.abs(coordinate.getX() - this.x) <= 2){
                return true;
            }
            if(Math.abs(coordinate.getY() - this.y) <= 2 && Math.abs(coordinate.getX() - this.x) <= 2){
                return true;
            }
            return false;
        }
    }

    private class CustomRenderListener implements RenderListener {

        @Override
        public void beginTextBlock() {

        }

        @Override
        public void renderText(TextRenderInfo textRenderInfo) {

        }

        @Override
        public void endTextBlock() {

        }

        @Override
        public void renderImage(ImageRenderInfo imageRenderInfo) {

        }
    }
}
