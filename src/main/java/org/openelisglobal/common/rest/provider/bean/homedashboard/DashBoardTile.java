package org.openelisglobal.common.rest.provider.bean.homedashboard;

import java.util.stream.Stream;

public class DashBoardTile {
    
    private String title;
    
    private String subTitle;
    
    private String value;

    public DashBoardTile(String title, String subTitle, int value) {
        this.title = title;
        this.subTitle = subTitle;
        this.value = String.valueOf(value);
    }
    
    public DashBoardTile(String title, String subTitle, String value) {
        this.title = title;
        this.subTitle = subTitle;
        this.value = value;
    }
    
    public String getTitle() {
        return title;
    }
    
    public void setTitle(String title) {
        this.title = title;
    }
    
    public String getSubTitle() {
        return subTitle;
    }
    
    public void setSubTitle(String subTitle) {
        this.subTitle = subTitle;
    }
    
    public String getValue() {
        return value;
    }
    
    public void setValue(String value) {
        this.value = value;
    }
    
    public enum TileType {
        
        ORDERS_IN_PROGRESS,
        ORDERS_READY_FOR_VALIDATION,
        ORDERS_COMPLETED_TODAY,
        ORDERS_PATIALLY_COMPLETED_TODAY,
        ORDERS_ENTERED_BY_USER_TODAY,
        ORDERS_REJECTED_TODAY,
        UN_PRINTED_RESULTS,
        INCOMING_ORDERS,
        AVERAGE_TURN_AROUND_TIME,
        DELAYED_TURN_AROUND;
        
        public static Stream<TileType> stream() {
            return Stream.of(TileType.values());
        }
    }
}
