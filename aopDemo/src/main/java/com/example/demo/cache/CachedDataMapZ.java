package com.example.demo.cache;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.Map.Entry;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;

public  class CachedDataMapZ<T> {
    private static Log log = LogFactory.getLog(CachedDataMapZ.class);
    protected Map<String, T> mapdata = new HashMap();
    protected char chr = '_';
    protected int sizelimit = 100;
    protected long deltatime = 86400000L;
    protected List<CachedDataMapZ<T>.KeyInfo> listkey = new ArrayList();

    public CachedDataMapZ() {
    }

    public CachedDataMapZ(int sizelimit) {
        this.sizelimit = sizelimit;
    }

    public CachedDataMapZ(int sizelimit, long deltatime) {
        this.sizelimit = sizelimit;
        this.deltatime = deltatime;
    }

    public T get(String... keys) {
        String key = this.genKey(keys);
        if (!this.validKey(key)) {
            return null;
        }

        return this.mapdata.get(key);
    }

    protected void put(String key, T val) {
        if (this.listkey.size() >= this.sizelimit) {
            CachedDataMapZ<T>.KeyInfo keyinfo = (CachedDataMapZ.KeyInfo)this.listkey.remove(0);
            this.mapdata.remove(keyinfo.name);
        }

        this.listkey.add(new CachedDataMapZ.KeyInfo(key, this.deltatime));
        this.mapdata.put(key, val);
    }

    protected void putAll(Map<String, T> data) {
        Iterator var3 = data.entrySet().iterator();

        while(var3.hasNext()) {
            Entry<String, T> entry = (Entry)var3.next();
            this.put((String)entry.getKey(), entry.getValue());
        }

    }

//    protected abstract T load(String... var1);

    private boolean validKey(String key) {
        this.cleanExpiredKey();
        return this.mapdata.containsKey(key);
    }

    private void cleanExpiredKey() {
        if (this.listkey.size() > 0) {
            while(((CachedDataMapZ.KeyInfo)this.listkey.get(0)).isExpired()) {
                CachedDataMapZ<T>.KeyInfo keyinfo = (CachedDataMapZ.KeyInfo)this.listkey.remove(0);
                this.mapdata.remove(keyinfo.name);
            }
        }

    }

    protected String genKey(String... keys) {
        StringBuffer string = new StringBuffer();
        for(String k : keys){
            string.append(k).append(this.chr);
        }
        return string.toString();
    }

    public void clear() {
        this.listkey.clear();
        this.mapdata.clear();
        log.info("清除缓存:" + this.getClass().getName());
    }

    public void setMyData(T t, String... keys) {
        String key = this.genKey(keys);
        this.put(key, t);
    }

    protected class KeyInfo {
        String name;
        long timeto;

        KeyInfo(String name, long delta) {
            this.name = name;
            this.timeto = System.currentTimeMillis() + delta;
            if (this.timeto < 0L) {
                this.timeto = 9223372036854775807L;
            }

        }

        boolean isExpired() {
            return System.currentTimeMillis() > this.timeto;
        }
    }
}
