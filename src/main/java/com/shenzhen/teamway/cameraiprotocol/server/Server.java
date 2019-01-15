package com.shenzhen.teamway.cameraiprotocol.server;

public interface Server {

 
	/**
	 * 
	 * @param listenPort
	 * @param bossThreadNum
	 * @param workerThreadNum
	 * @param service
	 * @throws Exception
	 */
	void start(int listenPort, final int bossThreadNum,
               final int workerThreadNum, final int businessThreadNum, String service)
			throws Exception;

	/**
	 * 停止服务
	 */
	void stop() throws Exception;

}
