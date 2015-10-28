package com.skandiacup.splinedevelopment.skandiacup.mappers;

import com.skandiacup.splinedevelopment.skandiacup.domain.RSSObject;

import org.xmlpull.v1.XmlPullParser;
import org.xmlpull.v1.XmlPullParserException;
import org.xmlpull.v1.XmlPullParserFactory;

import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;

/**
 * Created by borgarlie on 28/10/15.
 */

public class RSSMapper {
    public static ArrayList<RSSObject> mapRSS(byte[] xml) throws XmlPullParserException, IOException {
        XmlPullParserFactory xmlFactoryObject = XmlPullParserFactory.newInstance();
        XmlPullParser myparser = xmlFactoryObject.newPullParser();
        myparser.setFeature(XmlPullParser.FEATURE_PROCESS_NAMESPACES, false);
        InputStream is = new ByteArrayInputStream(xml);
        myparser.setInput(is, null);
        ArrayList<RSSObject> arr = parseXML(myparser);
        is.close();
        return arr;
    }

    public static ArrayList<RSSObject> parseXML(XmlPullParser myParser) {
        int event;
        String text = "";
        ArrayList<RSSObject> arr = new ArrayList<>();
        RSSObject current = new RSSObject();
        boolean is_item = false;
        try {
            event = myParser.getEventType();
            while (event != XmlPullParser.END_DOCUMENT) {
                String name=myParser.getName();
                switch (event){
                    case XmlPullParser.START_TAG:
                        break;
                    case XmlPullParser.TEXT:
                        text = myParser.getText();
                        break;
                    case XmlPullParser.END_TAG:
                        switch (name) {
                            case "item":
                                if (is_item) {
                                    arr.add(current);
                                } else {
                                    is_item = true;
                                }
                                current = new RSSObject();
                                break;
                            case "title":
                                current.setTitle(text);
                                break;
                            case "pubDate":
                                current.setPubDate(text);
                                break;
                            case "category":
                                current.setCategory(text);
                                break;
                            case "description":
                                current.setItemDescription(text);
                                break;
                            default:
                                break;
                        }
                        break;
                }
                event = myParser.next();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return arr;
    }
}
