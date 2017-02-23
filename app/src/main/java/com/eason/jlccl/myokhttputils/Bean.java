package com.eason.jlccl.myokhttputils;

import java.util.List;

/**
 * Created by cclej on 2017/2/23.
 */

public class Bean {
    /**
     * reason :
     * data : {"images":[{"pub_time":"2017-02-23","description":"来到熟悉的街道，依旧记得你暖心的笑容","description_en":"","height":"1280","photo_user":{"user_id":261575,"user_photo":"http://q.qlogo.cn/qqapp/101105874/3BF0EE059FCA0B8AB025DC5C0BE34374/100","user_name":"488893","user_link":""},"width":"1440","up_times":12093,"desc_user":{"user_id":530711,"user_photo":"http://q.qlogo.cn/qqapp/1102291665/39BF6B0D3D565E6FA13C1FD9CD5EBE62/100","user_name":"有生之年."},"image_url":"images/cbce84094c18c44c9db00769d52a9f08_1440x1280.jpg","id":21545}],"has_next":true,"base_url":"http://wpstatic.zuimeia.com/"}
     * result : 1
     */

    private String reason;
    /**
     * images : [{"pub_time":"2017-02-23","description":"来到熟悉的街道，依旧记得你暖心的笑容","description_en":"","height":"1280","photo_user":{"user_id":261575,"user_photo":"http://q.qlogo.cn/qqapp/101105874/3BF0EE059FCA0B8AB025DC5C0BE34374/100","user_name":"488893","user_link":""},"width":"1440","up_times":12093,"desc_user":{"user_id":530711,"user_photo":"http://q.qlogo.cn/qqapp/1102291665/39BF6B0D3D565E6FA13C1FD9CD5EBE62/100","user_name":"有生之年."},"image_url":"images/cbce84094c18c44c9db00769d52a9f08_1440x1280.jpg","id":21545}]
     * has_next : true
     * base_url : http://wpstatic.zuimeia.com/
     */

    private DataBean data;
    private int result;

    public String getReason() {
        return reason;
    }

    public void setReason(String reason) {
        this.reason = reason;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public int getResult() {
        return result;
    }

    public void setResult(int result) {
        this.result = result;
    }

    public static class DataBean {
        private boolean has_next;
        private String base_url;
        /**
         * pub_time : 2017-02-23
         * description : 来到熟悉的街道，依旧记得你暖心的笑容
         * description_en :
         * height : 1280
         * photo_user : {"user_id":261575,"user_photo":"http://q.qlogo.cn/qqapp/101105874/3BF0EE059FCA0B8AB025DC5C0BE34374/100","user_name":"488893","user_link":""}
         * width : 1440
         * up_times : 12093
         * desc_user : {"user_id":530711,"user_photo":"http://q.qlogo.cn/qqapp/1102291665/39BF6B0D3D565E6FA13C1FD9CD5EBE62/100","user_name":"有生之年."}
         * image_url : images/cbce84094c18c44c9db00769d52a9f08_1440x1280.jpg
         * id : 21545
         */

        private List<ImagesBean> images;

        public boolean isHas_next() {
            return has_next;
        }

        public void setHas_next(boolean has_next) {
            this.has_next = has_next;
        }

        public String getBase_url() {
            return base_url;
        }

        public void setBase_url(String base_url) {
            this.base_url = base_url;
        }

        public List<ImagesBean> getImages() {
            return images;
        }

        public void setImages(List<ImagesBean> images) {
            this.images = images;
        }

        public static class ImagesBean {
            private String pub_time;
            private String description;
            private String description_en;
            private String height;
            /**
             * user_id : 261575
             * user_photo : http://q.qlogo.cn/qqapp/101105874/3BF0EE059FCA0B8AB025DC5C0BE34374/100
             * user_name : 488893
             * user_link :
             */

            private PhotoUserBean photo_user;
            private String width;
            private int up_times;
            /**
             * user_id : 530711
             * user_photo : http://q.qlogo.cn/qqapp/1102291665/39BF6B0D3D565E6FA13C1FD9CD5EBE62/100
             * user_name : 有生之年.
             */

            private DescUserBean desc_user;
            private String image_url;
            private int id;

            public String getPub_time() {
                return pub_time;
            }

            public void setPub_time(String pub_time) {
                this.pub_time = pub_time;
            }

            public String getDescription() {
                return description;
            }

            public void setDescription(String description) {
                this.description = description;
            }

            public String getDescription_en() {
                return description_en;
            }

            public void setDescription_en(String description_en) {
                this.description_en = description_en;
            }

            public String getHeight() {
                return height;
            }

            public void setHeight(String height) {
                this.height = height;
            }

            public PhotoUserBean getPhoto_user() {
                return photo_user;
            }

            public void setPhoto_user(PhotoUserBean photo_user) {
                this.photo_user = photo_user;
            }

            public String getWidth() {
                return width;
            }

            public void setWidth(String width) {
                this.width = width;
            }

            public int getUp_times() {
                return up_times;
            }

            public void setUp_times(int up_times) {
                this.up_times = up_times;
            }

            public DescUserBean getDesc_user() {
                return desc_user;
            }

            public void setDesc_user(DescUserBean desc_user) {
                this.desc_user = desc_user;
            }

            public String getImage_url() {
                return image_url;
            }

            public void setImage_url(String image_url) {
                this.image_url = image_url;
            }

            public int getId() {
                return id;
            }

            public void setId(int id) {
                this.id = id;
            }

            public static class PhotoUserBean {
                private int user_id;
                private String user_photo;
                private String user_name;
                private String user_link;

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public String getUser_photo() {
                    return user_photo;
                }

                public void setUser_photo(String user_photo) {
                    this.user_photo = user_photo;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }

                public String getUser_link() {
                    return user_link;
                }

                public void setUser_link(String user_link) {
                    this.user_link = user_link;
                }
            }

            public static class DescUserBean {
                private int user_id;
                private String user_photo;
                private String user_name;

                public int getUser_id() {
                    return user_id;
                }

                public void setUser_id(int user_id) {
                    this.user_id = user_id;
                }

                public String getUser_photo() {
                    return user_photo;
                }

                public void setUser_photo(String user_photo) {
                    this.user_photo = user_photo;
                }

                public String getUser_name() {
                    return user_name;
                }

                public void setUser_name(String user_name) {
                    this.user_name = user_name;
                }
            }
        }
    }
}
