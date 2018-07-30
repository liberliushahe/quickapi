package com.vitea.domain;

import java.util.Date;

import javax.xml.bind.annotation.XmlRootElement;
/**
 * 知识库实体
 * @author liushahe
 *
 */
@XmlRootElement(name = "Knowledge")
public class Knowledge {
    /**
     *
     * 编号
     */
    private Short id;

    /**
     *标题
     */
    private String title;

    /**
     *作者
     */
    private String author;

    /**
     *发表日期
     */
    private Date time;

    /**
     *文件路径
     */
    private String filepath;

    /**
     *点击量
     */
    private Short hits;

    /**
     *来源
     */
    private String copyfrom;

    /**
     *内容
     */
    private String content;

	public Short getId() {
		return id;
	}

	public void setId(Short id) {
		this.id = id;
	}

	public String getTitle() {
		return title;
	}

	public void setTitle(String title) {
		this.title = title;
	}

	public String getAuthor() {
		return author;
	}

	public void setAuthor(String author) {
		this.author = author;
	}

	public Date getTime() {
		return time;
	}

	public void setTime(Date time) {
		this.time = time;
	}

	public String getFilepath() {
		return filepath;
	}

	public void setFilepath(String filepath) {
		this.filepath = filepath;
	}

	public Short getHits() {
		return hits;
	}

	public void setHits(Short hits) {
		this.hits = hits;
	}

	public String getCopyfrom() {
		return copyfrom;
	}

	public void setCopyfrom(String copyfrom) {
		this.copyfrom = copyfrom;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	@Override
	public String toString() {
		return "Knowledge [id=" + id + ", title=" + title + ", author=" + author + ", time=" + time + ", filepath="
				+ filepath + ", hits=" + hits + ", copyfrom=" + copyfrom + ", content=" + content + "]";
	}

   
   
}