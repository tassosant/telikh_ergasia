package com.unipi.tantoniou.telikh_ergasia.activities.dynamic;

import android.content.Context;
import android.speech.tts.TextToSpeech;

public class MyTts {

    private TextToSpeech tts;
    TextToSpeech.OnInitListener  initListener= new TextToSpeech.OnInitListener() {
        @Override
        public void onInit(int status) {
            if (status==TextToSpeech.SUCCESS){
                //tts.setLanguage(Locale.forLanguageTag("el"));
            }
        }
    };
    public MyTts(Context context){
        tts = new TextToSpeech(context,initListener);
    }
    public MyTts(){}
    public void speak(String message){
        tts.speak(message,TextToSpeech.QUEUE_ADD,null,null);
    }

    public void stopSpeaking(){
        tts.stop();
       // tts.speak(message,TextToSpeech.QUEUE_FLUSH,null,null);
    }


}
