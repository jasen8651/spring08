package dto;

public class PageDTO {
	
	private int currentPage; //현제
	private int totalCount; //총 레코드수
	private int blockCount =5; //한페이지에 보여줄 레코드수
	private int blockPage =3;// 한블록에 보여줄수있는 레코드수
	private int totalPage; // 총페이지수
	private int startRow;// 시작레코드번호
	private int endRow;//끝 레코드 번호
	private int startPage; //한블록 시작페이지
	private int endPage; //한블록 끝페이지
	private int number;
	
	private String searchKey;
	private String searchWord;
	
	public PageDTO() {
		
	}
	
	public PageDTO(int currentPage, int totalCount ) {
		this.currentPage = currentPage;
		this.totalCount = totalCount;
		
		totalPage = totalCount / blockCount + (totalCount % blockCount == 0 ? 0:1); //총페이지
		
		  if(totalPage<currentPage) this.currentPage = totalPage;
		 
		
		startRow = (this.currentPage -1) * blockCount+1;
		
		endRow = startRow + blockCount -1;
		
		
		
		
		startPage = (int) ((this.currentPage-1) / blockPage) * blockPage +1;//시작페이지
		endPage = startPage+blockPage-1; //끝페이지
		if(totalPage < endPage)
				endPage = totalPage;
		
		number = totalCount - (this.currentPage -1) * blockCount;
		
		
			
	}
	
	public PageDTO(int currentPage, int totalCount, String searchKey, String searchWord) {
		this(currentPage, totalCount);
		this.searchKey = searchKey;
		this.searchWord = searchWord;
	}

	public int getCurrentPage() {
		return currentPage;
	}

	public void setCurrentPage(int currentPage) {
		this.currentPage = currentPage;
	}

	public int getTotalCount() {
		return totalCount;
	}

	public void setTotalCount(int totalCount) {
		this.totalCount = totalCount;
	}

	public int getBlockCount() {
		return blockCount;
	}

	public void setBlockCount(int blockCount) {
		this.blockCount = blockCount;
	}

	public int getBlockPage() {
		return blockPage;
	}

	public void setBlockPage(int blockPage) {
		this.blockPage = blockPage;
	}

	public int getTotalPage() {
		return totalPage;
	}

	public void setTotalPage(int totalPage) {
		this.totalPage = totalPage;
	}

	public int getStartRow() {
		return startRow;
	}

	public void setStartRow(int startRow) {
		this.startRow = startRow;
	}

	public int getEndRow() {
		return endRow;
	}

	public void setEndRow(int endRow) {
		this.endRow = endRow;
	}

	public int getStartPage() {
		return startPage;
	}

	public void setStartPage(int startPage) {
		this.startPage = startPage;
	}

	public int getEndPage() {
		return endPage;
	}

	public void setEndPage(int endPage) {
		this.endPage = endPage;
	}

	public int getNumber() {
		return number;
	}

	public void setNumber(int number) {
		this.number = number;
	}

	public String getSearchKey() {
		return searchKey;
	}

	public void setSearchKey(String searchKey) {
		this.searchKey = searchKey;
	}

	public String getSearchWord() {
		return searchWord;
	}

	public void setSearchWord(String searchWord) {
		this.searchWord = searchWord;
	}

	
}
