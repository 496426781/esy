package org.ry8.CeaFrame.model;

import android.content.Context;
import org.ry8.external.androidquery.AQuery;
import org.ry8.external.androidquery.callback.AjaxCallback;

import java.util.Map;

import cn.ecailan.esy.ApiInterface;


public class BeeQuery<T> extends AQuery {
	public BeeQuery(Context context) {
		super(context);
		// TODO Auto-generated constructor stub
	}

	public static final int ENVIRONMENT_PRODUCTION = 1;
	public static final int ENVIROMENT_DEVELOPMENT = 2;
	public static final int ENVIROMENT_MOCKSERVER = 3;
	
	public static int environment() 
	{
		return ENVIROMENT_DEVELOPMENT;
	}
	
	public static String serviceUrl()
	{
		if (ENVIRONMENT_PRODUCTION == BeeQuery.environment()) 
		{
			return ApiInterface.serviceUrl_PRODUCTION;//"http://api.dribbble.com";
        }
		else 
		{
			return ApiInterface.serviceUrl_DEBUG;//"http://api.dribbble.com";//
        }
	}
	public <K> AQuery ajax(AjaxCallback<K> callback){

		if (BeeQuery.environment() == BeeQuery.ENVIROMENT_MOCKSERVER)
		{
			MockServer.ajax(callback);
			return null;
		}
        else
        {
            String url = callback.getUrl();
            String absoluteUrl = getAbsoluteUrl(url);

            callback.url(absoluteUrl);

        }

        if(BeeQuery.environment() == BeeQuery.ENVIROMENT_DEVELOPMENT)
        {
            DebugMessageModel.addMessage((BeeCallback)callback);
        }

		return (BeeQuery)super.ajax(callback);
	}

    public <K> AQuery ajaxAbsolute(AjaxCallback<K> callback){

        return (BeeQuery)super.ajax(callback);
    }

	public <K> AQuery ajax(String url, Map<String, ?> params, Class<K> type, BeeCallback<K> callback){
						
		callback.type(type).url(url).params(params);
		
		if (BeeQuery.environment() == BeeQuery.ENVIROMENT_MOCKSERVER)
		{
			MockServer.ajax(callback);
			return null;
		}
        else
        {
            String absoluteUrl = getAbsoluteUrl(url);
            callback.url(absoluteUrl);
        }
		return ajax(callback);
	}


    public static String getAbsoluteUrl(String relativeUrl) {
        return  BeeQuery.serviceUrl() + relativeUrl;
    }
}