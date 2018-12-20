# EOSForJava
A EOS library for Java

> # support java sdk >= 6 #


中文版文档：[https://blog.csdn.net/wypeng2010/article/details/85113370](https://blog.csdn.net/wypeng2010/article/details/85113370)

**- Use it**

1. first add Bip44ForJava dependencies

> implementation 'party.52it:Bip44ForJava:1.0.0'

2. generate EOS

## code: ##
```java
                    List<String> words = Bip44Utils.generateMnemonicWords();
                    Log.e("TAG", "words: " + words.toString());


                    BigInteger pri = Bip44Utils.getPathPrivateKey(words,"m/44'/194'/0'/0/0");
                    Log.e("TAG", "pri1: " + pri.toString(16));

                    String eospk =  Ecc.seedPrivate(pri);
                    Log.e("TAG", "EOS privateKey: " + eospk);

                    String eospuk =  Ecc.privateToPublic(eospk);
                    Log.e("TAG", "EOS publicKey: " + eospuk);
 
```
## result: ##
```base
2018-12-06 14:41:26.636 976-1101/? E/TAG: words: [cluster, page, museum, protect, bronze, leg, few, guide, sport, resource, luxury, magnet]
2018-12-06 14:41:27.107 976-1101/party.loveit.eosforandroid E/TAG: pri1: b021d8432d5c473b8b9be1b943de3effedff8cf2339bcb5c29b3031cca55316
2018-12-06 14:41:27.114 976-1101/party.loveit.eosforandroid E/TAG: EOS privateKey: 5Hu8mmzA4ud8sJFJy4ha5qqiDB36CVk5rBVc6bAEEHGuhDRfaF6
2018-12-06 14:41:27.187 976-1101/party.loveit.eosforandroid E/TAG: EOS publicKey: EOS6BpNg9SebtbeCFvkt1dZTQr4293gvdvpeRo9ZnDmHAZ3guNvGz
```

3. Specific test code

```java
System.out.println("============= Custom data signature ===============");
		String sign = Ecc.sign(pk,"is京東價as看到可可是是是@#￥%……&*（CVBNM《d ");
		System.out.println("sign :" + sign + " \n ");
		
		System.out.println("============= Transfer data serialization ===============");
		String data = Ecc.parseTransferData("fromaccount", "toaccount", "10.0020 SYS", "测试123abcdo./,./!@##$%");
		System.out.println("seriz data :" + data);
		System.out.println("transfer eq eosjs seriz " + data.equals(eosjs_transfer_seriz)+" \n ");

		System.out.println("============= Create account data serialization ===============");
		String data1 = Ecc.parseAccountData("eosio", "espritbloc1.","EOS8eAX54cJtAngV2V22WZhRCW7e4sTAZz1mC5U22vp8mAGuFdMXx","EOS8FPooohZiiCAYXahWCQRxgXXzUbS2gNELAeYCUgGdDMbd2FHQT");
		System.out.println("seriz data :" + data1);
		System.out.println("account eq eosjs seriz " + data1.equals(eosjs_account_seriz));

		
		System.out.println("\n******************* Ecc End *******************\n\n\n");
		
		System.out.println("******************* Rpc Start *******************\n");
		
		Rpc rpc = new Rpc("http://47.106.221.171:8888");
		
		System.out.println("============= Transfer ===============");
		try {
			Transaction t1 = rpc.transfer("5KQwrPbwdL6PhXujxW37FSSQZ1JiwsST4cqQzDeyXtP79zkvFD3","espritblocke", "inita","initb", "12.2821 MSP", "");
			System.out.println("转账成功 = " + t1.getTransactionId()+" \n ");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("============= Create an account and mortgage ===============");
		try {	
			Transaction t2 = rpc.createAccount("5KQwrPbwdL6PhXujxW37FSSQZ1JiwsST4cqQzDeyXtP79zkvFD3","eosio","ccccc..bbbbb", "EOS8eAX54cJtAngV2V22WZhRCW7e4sTAZz1mC5U22vp8mAGuFdMXx","EOS8eAX54cJtAngV2V22WZhRCW7e4sTAZz1mC5U22vp8mAGuFdMXx", 8192l, "0.01 SYS","0.01 SYS", 0l);
			System.out.println("创建成功 = " + t2.getTransactionId()+" \n ");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("============= Create an account without a mortgage ===============");
		try {	
			Transaction t3 = rpc.createAccount("5KQwrPbwdL6PhXujxW37FSSQZ1JiwsST4cqQzDeyXtP79zkvFD3","eosio","bbbb..54321", "EOS8eAX54cJtAngV2V22WZhRCW7e4sTAZz1mC5U22vp8mAGuFdMXx","EOS8eAX54cJtAngV2V22WZhRCW7e4sTAZz1mC5U22vp8mAGuFdMXx", 8192l);
			System.out.println("创建成功 = " + t3.getTransactionId()+" \n ");
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		
		System.out.println("============= Proxy voting ===============");
		try {
			List<String> produces = new ArrayList<>();
			produces.add("pppppeeeeooo");
			produces.add("mdddssssddds");
			produces.add("mdjddjddddds");
			rpc.voteproducer("5KQwrPbwdL6PhXujxW37FSSQZ1JiwsST4cqQzDeyXtP79zkvFD3", "epskdkdsddss","iuewjdkslsdc",produces);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		System.out.println("============= Turn off the token with a balance of 0 ===============");
		try {
			rpc.close("5KQwrPbwdL6PhXujxW37FSSQZ1JiwsST4cqQzDeyXtP79zkvFD3", "合约账户", "关闭账户", "0.0000 IPOS");
		}catch(ApiException e) {
			e.printStackTrace();
		}catch(Exception ex) {
			ex.printStackTrace();
		}
		System.out.println("\n******************* Rpc End *******************");

```
4. OfflineSign

```java
public static void testOfflineCreate() {
		Rpc rpc = new Rpc("http://47.106.221.171:8888");
		
		SignParam params = rpc.getOfflineSignParams(60l);
		
		OfflineSign sign = new OfflineSign();
		
		String content = "";
		try {
			content = sign.createAccount(params, "5KQwrPbwdL6PhXujxW37FSSQZ1JiwsST4cqQzDeyXtP79zkvFD3", "eeeeeeeeeeee",
					"555555555551", "EOS6MRyAjQq8ud7hVNYcfnVPJqcVpscN5So8BhtHuGYqET5GDW5CV",
					"EOS6MRyAjQq8ud7hVNYcfnVPJqcVpscN5So8BhtHuGYqET5GDW5CV", 8000l);
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Transaction tx = rpc.pushTransaction(content);
			System.out.println(tx.getTransactionId());
		} catch (ApiException ex) {
			System.out.println(ex.getError().getCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}

	public static void testOfflineTransfer() {
		Rpc rpc = new Rpc("http://47.106.221.171:8888");
		
		SignParam params = rpc.getOfflineSignParams(60l);
		
		OfflineSign sign = new OfflineSign();
		
		String content = "";
		try {
			content = sign.transfer(params, "5KQwrPbwdL6PhXujxW37FSSQZ1JiwsST4cqQzDeyXtP79zkvFD3", "eosio.token",
					"eeeeeeeeeeee", "555555555551", "372.0993 EOS", "test");
			System.out.println(content);
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		try {
			Transaction tx = rpc.pushTransaction(content);
			System.out.println(tx.getTransactionId());
		} catch (ApiException ex) {
			System.out.println(ex.getError().getCode());
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
```

 **- How to dependencies**
1. Maven

```base
<dependency>
  <groupId>party.52it</groupId>
  <artifactId>EOSForJava</artifactId>
  <version>1.0.0</version>
</dependency>
```
2. Gradle

```base
compile 'party.52it:EOSForJava:1.0.0'

or

implementation 'party.52it:EOSForJava:1.0.0'

```
3. Ivy

```base
<dependency org='party.52it' name='EOSForJava' rev='1.0.0'/>
```




 **- License**

     Copyright 2018 52it.party
    
    Licensed under the Apache License, Version 2.0 (the "License");
    you may not use this file except in compliance with the License.
    You may obtain a copy of the License at
    
       http://www.apache.org/licenses/LICENSE-2.0
    
    Unless required by applicable law or agreed to in writing, software
    distributed under the License is distributed on an "AS IS" BASIS,
    WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
    See the License for the specific language governing permissions and
    limitatio
