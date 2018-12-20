package party.loveit.eosforandroidlibrary.rpc.vo;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.util.List;
import java.util.Map;

@JsonIgnoreProperties(ignoreUnknown = true)
public class TableRows {

	private Boolean more;

	private List<Map> rows;

	public Boolean getMore() {
		return more;
	}

	public void setMore(Boolean more) {
		this.more = more;
	}

	public List<Map> getRows() {
		return rows;
	}

	public void setRows(List<Map> rows) {
		this.rows = rows;
	}
}
