## 本文中库的简单使用介绍
* 在project中添加依赖

      allprojects {
		repositories {
			...
			maven { url 'https://jitpack.io' }
		}
	   }
在app中添加依赖

        dependencies {
  	        compile 'com.github.jlcclidong:MyOkhttpUtils:0.10'
	    }
* 在本项目中使用okhttp的方法步骤
在application中初始化，此步骤必须，别忘记在清单文件中注册application否则不能使用
        Ok.init(this)
                .connectTimeout(3000l, TimeUnit.MILLISECONDS)
                .readTimeout(3000l, TimeUnit.MILLISECONDS)
                .commonParams("haha","1") //添加公共参数
                .commonHeader("nihao","1")  //添加公共头
                .AppInterceptor("eason", new LogInterceptor())   //应用拦截器
                .NetWorkInterceptor("eason",new LogInterceptor()) //网络拦截器 将重定向等的request response页拦截打印
                .CookieJar(new PersistentCookieJar(new SetCookieCache(), new SharedPrefsCookiePersistor(App.this)))
                .build();
* 请求  其中callback有三种类型 fail都会返回Exception，不管是本身网络问题还是404等问题都会返回此回调，可以通过Exception类型进行自行处理
  * callback有三种类型
    * JsonCallback<T> 传入类自动转换成bean，但是并未处理超大Json文件OOM 可自行通过流的方式来处理解析
    * CallBack 返回String
    * FileCallBack(String dir, String filename) 其中传入文件夹和文件名来保存 返回进度(0-100)
 * get
        Ok.get().url("http://lab.zuimeia.com/wallpaper/category/1/")
                .param("page_size", 1)
                .build()
                .call(new JsonCallBack<Bean>() {  //傳入實體類
                    @Override
                    public void fail(Exception e) {

                    }

                    @Override
                    public void success(Bean bean) {
                        mTv.setText(bean.getData().getBase_url());
                    }
                });
 * post  支持键值对和文件表单上传
        Ok.post().url("https://www.baidu.com")
                .param("test", 1)
                .file("test", new File(""))
                .build()
                .call(new CallBack() {
                    @Override
                    public void fail(Exception e) {

                    }

                    @Override
                    public void success(String response) {
                        mTv.setText(response);
                    }
                });
  * download 文件下载只做到最基本的下载,断点续传和暂停下载暂未做，后续会追加
         Ok.download().url("http://static.oschina.net/uploads/space/2015/0629/170157_rxDh_1767531.png")
                .build()
                .tag(MainActivity.this)
                .call(new FileCallBack(getCacheDir().getAbsolutePath(), "github.png") {
                    @Override
                    public void progress(int progress) {
                        mTv.setText(progress + "");
                    }

                    @Override
                    public void success(File file) {
                        mIV.setImageBitmap(BitmapFactory.decodeFile(file.getAbsolutePath()));
                    }

                    @Override
                    public void fail(Exception e) {

                    }
                });
  * 取消

         Ok.cancle(MainActivity.this);
 * 同时支持PostJson() 和 PostFile()两种方法
* 其中日志打印时使用了格式化JsonFormat工具类 
* 请求时自动添加了 Accept-Language 和 User-Agent
* 简单封装没有太多的代码，可以自行下载扩展
