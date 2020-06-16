package com.wbl.app.appifywp.ui;

import android.os.Build;
import android.os.Bundle;
import android.text.Html;
import android.text.method.LinkMovementMethod;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wbl.app.appifywp.R;

import androidx.annotation.Nullable;
import androidx.constraintlayout.widget.Constraints;
import androidx.fragment.app.Fragment;

public class InfoFragment extends Fragment {
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        //returning our layout file
        View root = inflater.inflate(R.layout.fragment_info, container, false);

        LinearLayout linearLayout = (LinearLayout)root.findViewById(R.id.text_credits);

        if(getString(R.string.cookie_policy_url) != null && !getString(R.string.cookie_policy_url).equals("")) {
            TextView cookiePolicy = new TextView(root.getContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                cookiePolicy.setText(Html.fromHtml("<a href=\"" + getString(R.string.cookie_policy_url) + "\">Cookie Policy</a>", Html.FROM_HTML_MODE_COMPACT));
            } else {
                cookiePolicy.setText(Html.fromHtml("<a href=\"" + getString(R.string.cookie_policy_url) + "\">Cookie Policy</a>"));
            }
            cookiePolicy.setMovementMethod(LinkMovementMethod.getInstance());
            cookiePolicy.setId(R.id.cookie_policy_item);
            cookiePolicy.setLayoutParams(new Constraints.LayoutParams(
                    Constraints.LayoutParams.MATCH_PARENT,
                    Constraints.LayoutParams.WRAP_CONTENT));
            cookiePolicy.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linearLayout.addView(cookiePolicy);
        }

        if(getString(R.string.privacy_policy_url) != null && !getString(R.string.privacy_policy_url).equals("")) {
            TextView privacyPolicy = new TextView(root.getContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                privacyPolicy.setText(Html.fromHtml("<a href=\"" + getString(R.string.privacy_policy_url) + "\">Privacy Policy</a>", Html.FROM_HTML_MODE_COMPACT));
            } else {
                privacyPolicy.setText(Html.fromHtml("<a href=\"" + getString(R.string.privacy_policy_url) + "\">Privacy Policy</a>"));
            }
            privacyPolicy.setMovementMethod(LinkMovementMethod.getInstance());
            privacyPolicy.setId(R.id.cookie_policy_item);
            privacyPolicy.setLayoutParams(new Constraints.LayoutParams(
                    Constraints.LayoutParams.MATCH_PARENT,
                    Constraints.LayoutParams.WRAP_CONTENT));
            privacyPolicy.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linearLayout.addView(privacyPolicy);
        }

        if(getString(R.string.terms_url) != null && !getString(R.string.terms_url).equals("")) {
            TextView terms = new TextView(root.getContext());
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.N) {
                terms.setText(Html.fromHtml("<a href=\"" + getString(R.string.privacy_policy_url) + "\">Termini e Condizioni</a>", Html.FROM_HTML_MODE_COMPACT));
            } else {
                terms.setText(Html.fromHtml("<a href=\"" + getString(R.string.privacy_policy_url) + "\">Termini e Condizioni</a>"));
            }
            terms.setMovementMethod(LinkMovementMethod.getInstance());
            terms.setId(R.id.cookie_policy_item);
            terms.setLayoutParams(new Constraints.LayoutParams(
                    Constraints.LayoutParams.MATCH_PARENT,
                    Constraints.LayoutParams.WRAP_CONTENT));
            terms.setTextAlignment(View.TEXT_ALIGNMENT_CENTER);
            linearLayout.addView(terms);
        }


        return root;
    }
}
