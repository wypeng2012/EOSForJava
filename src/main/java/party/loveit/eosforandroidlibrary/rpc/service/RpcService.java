package party.loveit.eosforandroidlibrary.rpc.service;

import java.util.Map;


import party.loveit.eosforandroidlibrary.rpc.vo.Block;
import party.loveit.eosforandroidlibrary.rpc.vo.ChainInfo;
import party.loveit.eosforandroidlibrary.rpc.vo.TableRows;
import party.loveit.eosforandroidlibrary.rpc.vo.TableRowsReq;
import party.loveit.eosforandroidlibrary.rpc.vo.account.Account;
import party.loveit.eosforandroidlibrary.rpc.vo.transaction.Transaction;
import party.loveit.eosforandroidlibrary.rpc.vo.transaction.push.TxRequest;
import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.POST;

/**
 * 
 * @author espritblock http://eblock.io
 *
 */
public interface RpcService {

	@GET("/v1/chain/get_info")
	Call<ChainInfo> getChainInfo();

	@POST("/v1/chain/get_block")
	Call<Block> getBlock(@Body Map<String, String> requestFields);

	@POST("/v1/chain/get_account")
	Call<Account> getAccount(@Body Map<String, String> requestFields);

	@POST("/v1/chain/push_transaction")
	Call<Transaction> pushTransaction(@Body TxRequest request);

	@POST("/v1/chain/get_table_rows")
	Call<TableRows> getTableRows(@Body TableRowsReq request);

}
