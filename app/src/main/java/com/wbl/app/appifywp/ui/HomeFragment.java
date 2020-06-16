package com.wbl.app.appifywp.ui;

import android.content.Intent;
import android.graphics.Bitmap;
import android.net.Uri;
import android.os.Bundle;
import android.text.TextUtils;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.webkit.WebResourceRequest;
import android.webkit.WebView;
import android.webkit.WebViewClient;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.annotation.NonNull;
import androidx.fragment.app.Fragment;
import androidx.lifecycle.Observer;
import androidx.lifecycle.ViewModelProviders;

import com.wbl.app.appifywp.R;

import java.net.URL;

public class HomeFragment extends Fragment {


    public View onCreateView(@NonNull LayoutInflater inflater,
                             ViewGroup container, Bundle savedInstanceState) {

        View root = inflater.inflate(R.layout.fragment_home, container, false);

        WebView webViewPost = root.findViewById(R.id.webViewMain);
        ProgressBar pbar = root.findViewById(R.id.pBarWebView);

        String url;
        Bundle args = getArguments();
        if(args != null)
            url = getArguments().getString("url");
        else
            url = getString(R.string.wp_site_url);

        String urlInternal = url;

        try {
            //post = ApiCalls.callGetSinglePost(idPost);
            webViewPost.getSettings().setJavaScriptEnabled(true);
            webViewPost.setWebViewClient(new WebViewClient() {
                @Override
                public boolean shouldOverrideUrlLoading(WebView view, WebResourceRequest request) {
                    boolean res = false;
                    try {
                        URL urlObj = new URL(request.getUrl().toString());
                        String host = getString(R.string.wp_site_url).split("//")[1];
                        host = host.replace("/","");
                        if(TextUtils.equals(urlObj.getHost(), host) ) {
                            //Allow the WebView in your application to do its thing
                            view.loadUrl(request.getUrl().toString());
                            res = false;
                        } else {
                            //Pass it to the system, doesn't match your domain
                            Intent intent = new Intent(Intent.ACTION_VIEW);
                            intent.setData(Uri.parse(urlInternal));
                            startActivity(intent);
                            //Tell the WebView you took care of it.
                            res = true;
                        }
                    }
                    catch (Exception e) {
                        e.printStackTrace();
                    }
                    return res;
                }
                @Override
                public void onPageStarted(WebView view, String url, Bitmap favicon){
                    // Do something on page loading started
                    // Visible the progressbar
                    webViewPost.setVisibility(View.INVISIBLE);
                    pbar.setVisibility(View.VISIBLE);
                }

                @Override
                public void onPageFinished(WebView view, String url){
                    // Do something when page loading finished
                    injectCSS(webViewPost);
                    pbar.setVisibility(View.INVISIBLE);
                    webViewPost.setVisibility(View.VISIBLE);
                }
            });

            /*String postContent = post.getContent().getRendered();
            postContent = HtmlUtils.replaceImageInHtml(postContent);
            webViewPost.loadDataWithBaseURL("", postContent, "text/html", "UTF-8", "");*/

            webViewPost.loadUrl(url);


        }
        catch (Exception ex) {
            ex.printStackTrace();
        }

        return root;
    }

    private void injectCSS(WebView webView) {
        try {
            String js =  "document.getElementsByClassName('" + getString(R.string.wp_menu_css_class) + "')[0].style.display='none';" +
                    "document.getElementsByClassName('" + getString((R.string.wp_footer_css_class)) + "')[0].style.display='none';";
            webView.loadUrl("javascript:(function() {" + js + "})()");
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
}