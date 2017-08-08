package io.github.adsuper.mytext1.webview.library;

import android.app.Activity;
import android.webkit.JavascriptInterface;

/**
 * Created by cenxiaozhong on 2017/5/24.
 */

public class AgentWebJsInterfaceCompat implements AgentWebCompat ,FileUploadPop<IFileUploadChooser> {

    private AgentWeb mAgentWeb;
    private Activity mActivity;
     AgentWebJsInterfaceCompat(AgentWeb agentWeb,Activity activity){
        this.mAgentWeb=agentWeb;
         this.mActivity=activity;
    }

    private IFileUploadChooser mIFileUploadChooser;
    @JavascriptInterface
    public void uploadFile(){


        mIFileUploadChooser=new FileUpLoadChooserImpl(mActivity,new FileUpLoadChooserImpl.JsChannelCallback() {
            @Override
            public void call(String value) {

                if(mAgentWeb!=null)
                    mAgentWeb.getJsEntraceAccess().quickCallJs("uploadFileResult",value);
            }
        });
        mIFileUploadChooser.openFileChooser();

    }

    @Override
    public IFileUploadChooser pop() {
        IFileUploadChooser mIFileUploadChooser=this.mIFileUploadChooser;
        this.mIFileUploadChooser=null;
        return mIFileUploadChooser;
    }
}
