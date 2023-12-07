package com.example.baby.my;

public class RankModel {

    private String userName;
    private int rankPoint;
    private int rank;

    public RankModel(String userName, int rankPoint) {
        this.userName = userName;
        this.rankPoint = rankPoint;
        this.rank = 0; // 기본값으로 초기화
    }

    @Override
    public String toString() {
        return userName +','+ rankPoint;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public int getRankPoint() {
        return rankPoint;
    }

    public void setRankPoint(int rankPoint) {
        this.rankPoint = rankPoint;
    }

    public int getRank() {
        return rank;
    }

    public void setRank(int rank) {
        this.rank = rank;
    }

}
