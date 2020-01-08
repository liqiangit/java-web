package com.hawkingfoo.model;

import java.io.Serializable;

/**
 * 
 * <p>
 * (chn,libraryName1)两个字段组合唯一确定一个分库，
 * </p>
 * <p>
 * (chn,libraryName1,libraryName2)三个字段组合唯一确定一个二级分库，
 * </p>
 * <p>
 * (peopleid,category)唯一确定一个人,
 * </p>
 * <p>
 * featureid唯一确定一个特征
 * </p>
 * <p>
 * (chn,libraryName1,libraryName2,featureid)唯一
 * </p>
 * @author liqiang
 *
 */
public class GroupDetail implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7227959501560653788L;

	/**
	 * 主键
	 */
    private Long id;

    private String chn;

    private String libraryName1;

    private String libraryName2;

    private String featureId;

    private String peopleId;

    private String category;

    private Integer x;

    private Integer y;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getChn() {
        return chn;
    }

    public void setChn(String chn) {
        this.chn = chn;
    }

    public String getLibraryName1() {
        return libraryName1;
    }

    public void setLibraryName1(String libraryName1) {
        this.libraryName1 = libraryName1;
    }

    public String getLibraryName2() {
        return libraryName2;
    }

    public void setLibraryName2(String libraryName2) {
        this.libraryName2 = libraryName2;
    }

    public String getFeatureId() {
        return featureId;
    }

    public void setFeatureId(String featureId) {
        this.featureId = featureId;
    }

    public String getPeopleId() {
        return peopleId;
    }

    public void setPeopleId(String peopleId) {
        this.peopleId = peopleId;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Integer getX() {
        return x;
    }

    public void setX(Integer x) {
        this.x = x;
    }

    public Integer getY() {
        return y;
    }

    public void setY(Integer y) {
        this.y = y;
    }
}