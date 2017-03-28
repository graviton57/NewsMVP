
package com.havrylyuk.newsmvp.data.source.local;

import android.content.ContentResolver;
import android.content.ContentUris;
import android.net.Uri;
import android.provider.BaseColumns;

import com.havrylyuk.newsmvp.BuildConfig;

/**
 *
 * Created by Igor Havrylyuk on 28.03.2017.
 */

public class NewsContract {

    public static final String CONTENT_AUTHORITY = BuildConfig.APPLICATION_ID;//"com.havrylyuk.newsmvp";

    // Use CONTENT_AUTHORITY to create the base of all URI's which apps will use to contact
    // the content provider.
    public static final Uri BASE_CONTENT_URI = Uri.parse("content://" + CONTENT_AUTHORITY);

    public static final String PATH_SOURCES = "sources";
    public static final String PATH_ARTICLES = "articles";

    public static final class SourcesEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_SOURCES).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SOURCES;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_SOURCES;

        // Table name
        public static final String TABLE_NAME = "sources";

        public static final String SOURCE_ID       = "source_id";
        public static final String SOURCE_NAME     = "name";
        public static final String SOURCE_DESC     = "description";
        public static final String SOURCE_CATEGORY = "category";
        public static final String SOURCE_URL      = "url";
        public static final String SOURCE_LOGO     = "logo";

        public static Uri buildSourceUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static long getIdFromUri(Uri uri) {
            return Long.parseLong(uri.getPathSegments().get(1));
        }
    }

    public static final class ArticlesEntry implements BaseColumns {

        public static final Uri CONTENT_URI =
                BASE_CONTENT_URI.buildUpon().appendPath(PATH_ARTICLES).build();

        public static final String CONTENT_TYPE =
                ContentResolver.CURSOR_DIR_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ARTICLES;
        public static final String CONTENT_ITEM_TYPE =
                ContentResolver.CURSOR_ITEM_BASE_TYPE + "/" + CONTENT_AUTHORITY + "/" + PATH_ARTICLES;

        // Table name
        public static final String TABLE_NAME = "articles";

        public static final String ART_SOURCE   = "source";
        public static final String ART_AUTHOR   = "author";
        public static final String ART_IMAGE    = "image_url";
        public static final String ART_TITLE    = "title";
        public static final String ART_DESC     = "description";
        public static final String ART_URL      = "url";
        public static final String ART_DATE     = "pub_date";

        public static Uri buildArticleUri(long id) {
            return ContentUris.withAppendedId(CONTENT_URI, id);
        }

        public static long getIdFromUri(Uri uri) {
            return Long.parseLong(uri.getPathSegments().get(1));
        }
    }
}
