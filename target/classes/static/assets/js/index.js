 //模块入口
 layui.config({
    base: '../assets/plugins/layuiAdmin/js/' //静态资源所在路径
  }).extend({
    index: 'lib/index' //主入口模块
  }).use('index');