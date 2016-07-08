##NewsTemplate

###介绍
仿知乎日报新闻详情页

###特性
 - artTemplate模板引擎
 - 头部图片滚动视觉差
 - HTML中图片绑定点击事件
 
###步骤
####编写模板
```javascript
<script id="contenttpl" type="text/html">
        {{#body}}
</script>
```

####渲染模板
```html
<div id="contentTop"></div>
<script>
    var data =JSON.parse(window.bazhang.getNewsData());
    var html = template('contenttpl', data);
    document.getElementById('contentTop').innerHTML = html;
</script>
```

####加载本地网页
```java
mWebNews.loadUrl("file:///android_asset/newscont.html");
```

###使用到的开源库
 - [artTemplate](https://github.com/aui/artTemplate)
 - [butterknife](https://github.com/JakeWharton/butterknife)
 - [RxAndroid](https://github.com/ReactiveX/RxAndroid)
 - [Android-ObservableScrollView](https://github.com/ksoichiro/Android-ObservableScrollView)
 - [NineOldAndroids](https://github.com/JakeWharton/NineOldAndroids)
 - [glide](https://github.com/bumptech/glide)
 - [fastjson](https://github.com/alibaba/fastjson)

###预览
![Markdown](http://i4.piimg.com/1070/a12ea34b00b582e9.gif)
![Markdown](http://i4.piimg.com/1070/d52360f6efd45715.png =270x480)
![Markdown](http://i4.piimg.com/1070/c98ec8fd14931024.png =270x480)

###**Lincense**
```lincense
Copyright [2016] [JeasonWong of copyright owner]

Licensed under the Apache License, Version 2.0 (the "License");
you may not use this file except in compliance with the License.
You may obtain a copy of the License at

    http://www.apache.org/licenses/LICENSE-2.0

Unless required by applicable law or agreed to in writing, software
distributed under the License is distributed on an "AS IS" BASIS,
WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
See the License for the specific language governing permissions and
limitations under the License.
```