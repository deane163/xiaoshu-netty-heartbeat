package cn.myzf.server.config;


/**
 * 功能说明：
 *
 * @ com.ubtechinc.common
 * <p>
 * Original @Author: deane.jia-贾亮亮,@2020/4/30@15:04
 * <p>
 * Copyright (C)2012-@2020 小树盛凯科技 All rights reserved.
 */
final public class MessageType {

    // MessageContent 中 content 为 StringValue
    public static final String TEXT = "text";
    public static final String VOICE = "voice";
    public static final String VIDEO = "video";
    public static final String IMAGE = "image";
    public static final String EMOTION = "emotion";
    public static final String FILE = "file";
    public static final String LOCATION = "location";
    public static final String RICH_CONTENT = "richcontent";
    // MessageContent 中 content 为 BytesValue
    public static final String CUSTOM = "custom";
    // MessageContent 中 content 为 OnlineStatusNotification
    public static final String ONLINE_STATUS_NOTIFICATION = "status";
    // MessageContent 中 content 为 ContactNotification
    public static final String CONTACT_NOTIFICATION = "contact";
    // MessageContent 中 content 为 GroupNotification
    public static final String GROUP_NOTIFICATION = "group";
    // MessageContent 中 content 为 PrivateNotification
    public static final String PRIVATE_NOTIFICATION = "private";
    // MessageContent 中 content 为 ForceOfflineNotification
    public static final String FORCE_OFFLINE_NOTIFICATION = "offline";
    // MessageContent 中 content 为 ReceiptNotification
    public static final String RECEIPT_NOTIFICATION = "receipt";

    private MessageType() {
    }

    public static final class OnlineStatusType {
        public static final String STATUS_ONLINE = "status/online";
        public static final String STATUS_OFFLINE = "status/offline";

        private OnlineStatusType() {
        }
    }

    public static final class ContactNotificationType {
        public static final String CONTACT_REQUEST = "contact/request";
        public static final String CONTACT_ACCEPT = "contact/accept";
        public static final String CONTACT_REJECT = "contact/reject";
        public static final String CONTACT_DELETE = "contact/delete";
        public static final String CONTACT_IGNORE = "contact/ignore";

        private ContactNotificationType() {
        }
    }

    public static final class GroupNotificationType {
        public static final String GROUP_RENAME = "group/name-changed";
        public static final String GROUP_TRANSFER = "group/owner-changed";
        public static final String GROUP_ADD_MEMBERS = "group/member-add";
        public static final String GROUP_REMOVE_MEMBERS = "group/member-remove";
        public static final String GROUP_QUIT = "group/member-quit";
        public static final String GROUP_DISMISS = "group/dismiss";

        private GroupNotificationType() {
        }
    }

    public static final class ForceOfflineNotificationType {
        public static final String OFFLINE_KICKED = "offline/kicked";
        public static final String OFFLINE_TOKEN_EXPIRED = "offline/token-expired";

        private ForceOfflineNotificationType() {

        }
    }

    public static final class ReceiptNotificationType {
        public static final String RECEIPT_UNREACHABLE = "receipt/unreachable";
        public static final String RECEIPT_SUCCESS = "receipt/success";
        public static final String RECEIPT_FAILED = "receipt/failed";

        private ReceiptNotificationType() {

        }
    }

    public static final class BatchPrivateType {
        public static final String PRIVATE_TEXT = "text";
        public static final String BATCH_SUCCESS = "batch/success";
        public static final String BATCH_FAILED = "batch/failed";

        private BatchPrivateType() {

        }
    }
}
