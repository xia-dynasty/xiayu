package com.naturelaw.system.interceptor;

import okhttp3.Interceptor;
import okhttp3.Request;
import okhttp3.Response;

import java.io.IOException;
import java.util.Map;
import java.util.Set;

/**
 * description:构建基础拦截器,用来设置基础header，这里是通过MAP键值对来构建，将heder加入到Request中。
 *
 * @author Andy
 * @version 1.0
 * @date 07/07/2021 23:59
 */
public class BaseInterceptor implements Interceptor {
	private Map<String, String> headers;

	public BaseInterceptor(Map<String, String> headers) {
		this.headers = headers;
	}

	@Override
	public Response intercept(Chain chain) throws IOException {

		Request.Builder builder = chain.request().newBuilder();
		if (headers != null && headers.size() > 0) {
			Set<String> keys = headers.keySet();
			for (String headerKey : keys) {
				builder.addHeader(headerKey, headers.get(headerKey)).build();
			}
		}
		return chain.proceed(builder.build());
	}
}
