package com.example.pdf;

import java.awt.geom.Rectangle2D;
import java.io.IOException;
import java.util.List;

public class Test {
    public static void main(String[] args) throws Exception {
        PdfResolver pdfResolver = new PdfResolver("E:\\Doc\\业务资料\\11574139_ProjectPlan.pdf");
        int memberPage = pdfResolver.getPageOfKeyWords("项目组主要成员","项目分工");
        List<String> result = pdfResolver.resolveTable(memberPage,11);
        System.out.println(result);
    }
}
