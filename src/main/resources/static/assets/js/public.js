/*
*=====SAAS页面公共javascript
*=====2019/04/01
*/

// 处理html标签在IE浏览器下添加对应的class
$(function () {
    if (/*@cc_on!@*/false) {
        document.documentElement.className += ' ie' + document.documentMode;// 针对IE10
    };
    if (/*@cc_on!@*/true) {
        document.documentElement.className += ' ie' + document.documentMode;// 针对IE11以下及非IE浏览器
    };
    if (window.ActiveXObject || "ActiveXObject" in window) {// 判断是否为IE浏览器，如果是IE就在html标签上面加上类名“IE”，如果不是则不添加
        $('html').addClass('IE');
    };
});

// 加载更多
$('.flow-see-more').click(function () {
    $(this).hide();
    $('.flow-loading-more').show();
    setTimeout(function () {
        $('.flow-see-more').show();
        $('.flow-loading-more').hide();
    },1000);
});

// 解决弹窗锁定/解锁滚动条时页面抖动
function lockScrollbar() {
    //获取滚动条宽度
    var scrollbarWidth = window.innerWidth - document.documentElement.clientWidth;
    console.log("当前滚动条宽度为:" + scrollbarWidth + "px");
    $('body').addClass('scrollbar lock-scrollbar');
    // 弹出弹窗时给head插入style
    $("head").append(
        '<style id="lock-style-noscroll" type="text/css">.lock-scrollbar{margin-right:' + (scrollbarWidth) + "px;}</style>"
    );
};
// 解锁滚动条
function unlockScrollbar() {
    $('body').removeClass('scrollbar lock-scrollbar');
    // 弹出弹窗时给head插入style
    $("#lock-style-noscroll").remove();
};

// 数字滚动
$(document).ready(function(){
    $('.scoreNumber').each(function(){
        $(this).prop('Counter',0).animate({
            Counter: $(this).text()
        },{
            duration: 2000,
            easing: 'swing',
            step: function (now){
                $(this).text(Math.ceil(now));
            }
        });
    });
});


// 意见反馈
function openFeedBack() {
    var feedBackHtml =
        '<div class="popup-layer fixed feed-back-main">\n' +
        '    <div class="mask"></div>\n' +
        '    <div class="popup-layer-content">\n' +
        '        <div class="popup-layer-header">\n' +
        '            <i class="saas sa-close close-popup animate" onclick="closeFeedBack();"></i>\n' +
        '            <div class="title">请留下您的宝贵意见</div>\n' +
        '        </div>\n' +
        '        <div class="popup-layer-body">\n' +
        '            <div class="popup-layer-wrap">\n' +
        '               <div class="feed-back-wrap">\n' +
        '                    <div class="feed-back-title">产品建议</div>\n' +
        '                   <div class="feed-back-form">\n' +
        '                       <div class="layui-form">\n' +
        '                           <div class="layui-form-item">\n' +
        '                               <div class="layui-input-inline">\n' +
        '                                   <input type="radio" name="productType" value="行业百科" title="行业百科">\n' +
        '                               </div>\n' +
        '                               <div class="layui-input-inline">\n' +
        '                                   <input type="radio" name="productType" value="行业百科" title="行业百科">\n' +
        '                               </div>\n' +
        '                               <div class="layui-input-inline">\n' +
        '                                   <input type="radio" name="productType" value="行业百科" title="行业百科">\n' +
        '                               </div>\n' +
        '                               <div class="layui-input-inline">\n' +
        '                                   <input type="radio" name="productType" value="行业百科" title="行业百科">\n' +
        '                               </div>\n' +
        '                               <div class="layui-input-inline">\n' +
        '                                   <input type="radio" name="productType" value="行业百科" title="行业百科">\n' +
        '                               </div>\n' +
        '                               <div class="layui-input-inline">\n' +
        '                                   <input type="radio" name="productType" value="行业百科" title="行业百科">\n' +
        '                               </div>\n' +
        '                               <div class="layui-input-inline">\n' +
        '                                   <input type="radio" name="productType" value="行业百科" title="行业百科">\n' +
        '                               </div>\n' +
        '                               <div class="layui-input-inline">\n' +
        '                                   <input type="radio" name="productType" value="行业百科" title="行业百科">\n' +
        '                               </div>\n' +
        '                               <div class="layui-input-inline">\n' +
        '                                   <input type="radio" name="productType" value="行业百科" title="行业百科">\n' +
        '                               </div>\n' +
        '                               <div class="layui-input-inline">\n' +
        '                                   <input type="radio" name="productType" value="行业百科" title="行业百科">\n' +
        '                               </div>\n' +
        '                           </div>\n' +
        '                       </div>\n' +
        '                   </div>\n' +
        '                   <div class="feed-back-title mt40">其他建议</div>\n' +
        '                   <div class="feed-back-form">\n' +
        '                       <div class="layui-form">\n' +
        '                           <div class="layui-form-item">\n' +
        '                               <div class="layui-input-inline">\n' +
        '                                   <input type="radio" name="productType" value="其他" title="其他">\n' +
        '                               </div>\n' +
        '                           </div>\n' +
        '                       </div>\n' +
        '                   </div>\n' +
        '                   <div class="feed-back-textarea">\n' +
        '                       <div class="userInfo-row">\n' +
        '                           <div class="input-main">\n' +
        '                               <div class="input-content">\n' +
        '                                   <div class="input-box textarea-input-box">\n' +
        '                                       <textarea placeholder="亲爱的用户：请在这里直接填写您遇到的问题或意见建议，您的意见是我们前进的动力" class="layui-textarea"></textarea>\n' +
        '                                   </div>\n' +
        '                               </div>\n' +
        '                           </div>\n' +
        '                       </div>\n' +
        '                   </div>\n' +
        '               </div>\n' +
        '                <div class="popup-layer-btn text-center pt10">\n' +
        '                    <a href="javascript:;" class="layui-btn layui-btn-normal w100" onclick="closeFeedBack();">提&nbsp;&nbsp;交</a>\n' +
        '                    <a href="javascript:;" class="layui-btn layui-btn-primary w100" onclick="closeFeedBack();">取&nbsp;&nbsp;消</a>\n' +
        '                </div>\n' +
        '            </div>\n' +
        '        </div>\n' +
        '    </div>\n' +
        '</div>';
    $("body").append(feedBackHtml);
    // 重新加载form
    layui.use('form',function(){
        var form=layui.form;
        form.render('radio');
    });
    lockScrollbar();
}
function closeFeedBack() {
    $('.feed-back-main').remove();
    unlockScrollbar();
}