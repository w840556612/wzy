package com.ipinyou.optimus.console.report.entity;

/**
 * 日志分析中，到达、转化的分析状态
 * @author ganwenlong  2013.12.04
 */
final class BaseLogStatus {

	public static interface MatchImp {
		public boolean isMatchImp();
		public void setMatchImp(boolean matchImp);
	}
	public static interface MatchClick {
		public boolean isMatchClick();
		public void setMatchClick(boolean matchClick);
	}
	public static interface MatchDsp {
		public boolean isMatchDsp();
		public void setMatchDsp(boolean matchDsp);
	}
	public static interface ClientConfirmPy {
		public boolean isClientConfirmPy();
		public void setClientConfirmPy(boolean clientConfirmPy);
	}

	
	public static interface ClickActionStatus extends ClientConfirmPy, MatchClick, MatchDsp {
	}

	public static interface ImpActionStatus extends ClientConfirmPy, MatchImp, MatchDsp {
	}
	
}
