package ir.freeland.springboot.model;

public class ErrorRecord {
	private String fileName;
	private int recordNumber;
	private String errorCode;
	private String errorClassification;
	private String errorDescription;
	private String errorDate;
	public String getFileName() {
		return fileName;
	}
	public void setFileName(String fileName) {
		this.fileName = fileName;
	}
	public int getRecordNumber() {
		return recordNumber;
	}
	public void setRecordNumber(int recordNumber) {
		this.recordNumber = recordNumber;
	}
	public String getErrorCode() {
		return errorCode;
	}
	public void setErrorCode(String errorCode) {
		this.errorCode = errorCode;
	}
	public String getErrorClassification() {
		return errorClassification;
	}
	public void setErrorClassification(String errorClassification) {
		this.errorClassification = errorClassification;
	}
	public String getErrorDescription() {
		return errorDescription;
	}
	public void setErrorDescription(String errorDescription) {
		this.errorDescription = errorDescription;
	}
	public String getErrorDate() {
		return errorDate;
	}
	public void setErrorDate(String errorDate) {
		this.errorDate = errorDate;
	}
	

	@Override
	public String toString() {
		return "ErrorRecord [fileName=" + fileName + ", recordNumber=" + recordNumber + ", errorCode=" + errorCode
				+ ", errorClassification=" + errorClassification + ", errorDescription=" + errorDescription
				+ ", errorDate=" + errorDate + "]";
	}

}
