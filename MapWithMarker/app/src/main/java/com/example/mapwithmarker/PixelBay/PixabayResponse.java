package com.example.mapwithmarker.PixelBay;

import java.util.List;

public class PixabayResponse {
    private int totalHits;
    private List<PixabayImage> hits;

    public int getTotalHits() {
        return totalHits;
    }

    public void setTotalHits(int totalHits) {
        this.totalHits = totalHits;
    }

    public List<PixabayImage> getHits() {
        return hits;
    }

    public void setHits(List<PixabayImage> hits) {
        this.hits = hits;
    }
}
