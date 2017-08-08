package com.zoom.bundle_loading.ProgressBar;

import android.content.Context;
import android.content.Intent;


public class IntentUtils
{
    /**
     * ��������
     */
    public static final int HANDLER_LEFT = 0;

    /**
     * ��������
     */
    public static final int HANDLER_RIGHT = 1;

    /**
     * ��ȷ�����
     */
    public static final int HANDLER_INDETER = 2;

    /**
     * IntentUtils����
     */
    private static IntentUtils mIntentUtils;

    /**
     * <һ�仰���ܼ���>��ȡIntentUtils����<BR>
     * <������ϸ����>
     * 
     * @return
     * @see [�ࡢ��#��������#��Ա]
     */
    public static IntentUtils getInstance()
    {
        if (mIntentUtils == null)
        {
            mIntentUtils = new IntentUtils();
        }
        return mIntentUtils;
    }

    /**
     * <һ�仰���ܼ���>Intentҳ����ת<BR>
     * <������ϸ����>
     * 
     * @param context
     * @param mclass
     * @see [�ࡢ��#��������#��Ա]
     */
    public void intentForward(Context context, Class<?> mclass)
    {
        Intent intent = new Intent();
        intent.setClass(context, mclass);
        context.startActivity(intent);
    }
}
