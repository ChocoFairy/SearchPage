package javaBean;

import java.io.Serializable;

public class PageBean implements Serializable{
	private int numInPage = 5;     // 一页的记录数
	private int totalPage;    // 总页数
	private int currentPageNum;   // 当前的页号
	private int totalNum;       // 查询的总记录数
	private int start;     // 当前分页开始项的序号
	private int end;       // 当前分页结束项的序号
	private int currentNum;   // 当前页面显示数
	
	public PageBean() {}

	public PageBean(int numInPage, int totalPage, int currentPageNum, int totalNum, int start, int end) {
		super();
		this.numInPage = numInPage;
		this.totalPage = totalPage;
		this.currentPageNum = currentPageNum;
		this.totalNum = totalNum;
		this.start = start;
		this.end = end;
	}



	public int getNumInPage() {
		return numInPage;
	}

	public void setNumInPage(int numInPage) {
		this.numInPage = numInPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getCurrentPageNum() {
		return currentPageNum;
	}

	public void setCurrentPageNum(int currentPageNum) {
		this.currentPageNum = currentPageNum;
	}

	public int getTotalNum() {
		return totalNum;
	}

	public void setTotalNum(int totalNum) {
		this.totalNum = totalNum;
	}

	public int getStart() {
		return start;
	}

	public void setStart(int start) {
		this.start = start;
	}

	public int getEnd() {
		return end;
	}

	public void setEnd(int end) {
		this.end = end;
	}
	

	public int getCurrentNum() {
		return currentNum;
	}

	public void setCurrentNum(int currentNum) {
		this.currentNum = currentNum;
	}

	@Override
	public String toString() {
		return "PageBean [totalPage=" + totalPage + ", currentPageNum=" + currentPageNum + ", totalNum=" + totalNum + "]";
	}
	
	public void showInfo() {
		totalPage =totalNum/numInPage+(totalNum%numInPage==0?0:1);
		System.out.println("num_in_page:"+numInPage);
		System.out.println("currentpagenum:"+currentPageNum);
		System.out.println("totalNum:"+totalNum);
	}
	
	public void pageStrategy() {
		System.out.println(totalPage);
		start = currentPageNum*numInPage;
		if(currentPageNum*numInPage+numInPage>totalNum) {
			end = totalNum-1;
		}else {
			end = currentPageNum*numInPage+numInPage-1;
		}
		//当前页面显示数
		if(currentPageNum<totalPage-1) {
			currentNum = numInPage;
		}else if(currentPageNum==totalPage-1) {
			currentNum = totalNum - currentPageNum*numInPage;
		}
		System.out.println("currentNum:"+currentNum);
		System.out.println("start:"+start);
		System.err.println("end:"+end);
	}
}
