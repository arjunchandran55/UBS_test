package com.ubs.opsit.interviews;

public class BerlinTimeCalculator implements TimeConverter {
	int hh;
	int mm;
	int ss;	

	@Override
	public String convertTime(String aTime) {
	
		String[] timedata = aTime.split(":");
		this.hh = Integer.parseInt(timedata[0]);
		this.mm = Integer.parseInt(timedata[1]);
		this.ss = Integer.parseInt(timedata[2]);
		
		String lampTop =  getLampRepresentation(Constants.YELLOW_LAMP, Constants.TOP_LAMP_COUNT, Constants.TOP_LAMP );
		String lampRow1 =  getLampRepresentation(Constants.RED_LAMP, Constants.TOP_ROW_LAMP_1_COUNT, Constants.TOP_ROW_LAMP_1 );
		String lampRow2 =  getLampRepresentation(Constants.RED_LAMP, Constants.TOP_ROW_LAMP_2_COUNT, Constants.TOP_ROW_LAMP_2 );
		String lampBottomRow1 =  getLampRepresentation(Constants.YELLOW_LAMP, Constants.BOTTOM_ROW_LAMP_1_COUNT, Constants.BOTTOM_ROW_LAMP_1 );
		String lampBottomRow2 =  getLampRepresentation(Constants.YELLOW_LAMP, Constants.BOTTOM_ROW_LAMP_2_COUNT, Constants.BOTTOM_ROW_LAMP_2 );
		
		return lampTop + "\r\n" + lampRow1 + "\r\n" + lampRow2 + "\r\n" + lampBottomRow1 + "\r\n" + lampBottomRow2 ;
	}
	
	public String getLampRepresentation(String lampColor, int totalNoOfLamps, String lampRowType) {
		int onLamps = 0;
		switch(lampRowType) {
			case Constants.TOP_LAMP:
				if (this.ss % 2 == 0) return lampColor;
				else return Constants.OFF_LAMP;
			case Constants.TOP_ROW_LAMP_1:
				onLamps = this.hh / 5;
				return buildOnOfRepresentation(lampColor, totalNoOfLamps, onLamps, lampRowType);
			case Constants.TOP_ROW_LAMP_2: 
				onLamps = this.hh % 5;
				return buildOnOfRepresentation(lampColor, totalNoOfLamps, onLamps, lampRowType);
			case Constants.BOTTOM_ROW_LAMP_1: 
				onLamps = this.mm / 5;
				return buildOnOfRepresentation(lampColor, totalNoOfLamps, onLamps, lampRowType);
			case Constants.BOTTOM_ROW_LAMP_2:
				onLamps = this.mm % 5;
				return buildOnOfRepresentation(lampColor, totalNoOfLamps, onLamps, lampRowType);
		}
		return "";
	}

	private String buildOnOfRepresentation(String lampColor, int totalNoOfLamps, int onLamps, String lampRowType) {
		// TODO Auto-generated method stub
		String tempLampColor = "";
		StringBuilder str = new StringBuilder();
		for (int i=1; i <= totalNoOfLamps; i++) {
			// TOGGLE BETWEEN YELLOW AND RED FOR 11 COLOR LAMP PANEL
			tempLampColor = (lampRowType.equals(Constants.BOTTOM_ROW_LAMP_1) && (i % 3 == 0)) ? Constants.RED_LAMP : "";
			// MARK A LAMP ON - OFF 
			str = (i <= onLamps) ? str.append(tempLampColor != "" ? tempLampColor : lampColor) :	str.append(Constants.OFF_LAMP);
		}
		return str.toString();
	}
}
