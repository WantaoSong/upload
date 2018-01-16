package com.upload.v3.entity;


public class Limit {


	private int start;

	private int size;

	private static final int MIN_START = 0;

	private static final int MIN_SIZE = 10;

	public Limit( int start, int size) {
		if (start < 0 ){
			start = MIN_START;
		}else if (size <= 0 ){
			size = MIN_SIZE;
		}else{
			this.start = start;
			this.size = size;
		}
		
	}

	

	public void setStart(int start) {
		this.start = start;
	}



	public void setSize(int size) {
		this.size = size;
	}



	public int getStart() {
		return start;
	}

	public int getSize() {
		return size;
	}

	@Override
	public String toString() {
		return "Limit{" +
				", start=" + start +
				", size=" + size +
				'}';
	}

}
