// 订单状态
var ORDER_STATUS = {
    1: "已付款",
    2: "待付款",
    3: "已取消",
    4: "未取消"
};

// 提现功能：申请记录状态
var CASH_STATUS = {
    "1": "待处理",
    "2": "失败",
    "3": "成功"
};
// 跳转类型
var JUMP_TYPE = {
    "1": "页面",
    "2": "商品",
    "3": "分类"
}
//显示状态
var STATUS_VISIBLE = {
    "1": "显示",
    "2": "隐藏"
}

// option：首页相关统计图
var homepageOption = {
    title: {
        text: 'FAC数据总体一览'
    },
    color: ['#3398DB'],
    tooltip: {
        trigger: 'axis',
        axisPointer: {            // 坐标轴指示器，坐标轴触发有效
            type: 'shadow'        // 默认为直线，可选为：'line' | 'shadow'
        }
    },
    toolbox: {
        show: true,
        top: 10,
        right: 10,
        feature: {
            mark: {show: true},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: [
        {
            type: 'category',
            data: [], // 后端重新附值
            axisTick: {
                alignWithLabel: true
            }
        }
    ],
    yAxis: [
        {
            type: 'value'
        }
    ],
    series: [
        {
            name: '',
            type: 'bar',
            barWidth: '60%',
            data: [] // 后端重新附值
        }
    ]
};

// 订单统计
var orderOption = {
    title: {
        text: '每日订单金额与数量统计'
    },
    tooltip: {
        trigger: 'item',
        formatter: "{a} <br/>{b} : {c}"
    },
    toolbox: {
        show: true,
        top: 10,
        right: 10,
        feature: {
            mark: {show: true},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {
        top: 32,
        left: 'center',
        data: ['订单金额', '订单数量']
    },
    calculable: true,
    xAxis: [
        {
            type: 'category',
            data: []
        }
    ],
    yAxis: [
        {
            type: 'value',
            name: "订\n单\n金\n额\n︵\n元\n︶",
            nameLocation: "center",
            nameGap: 35,
            nameRotate: 0,
            nameTextStyle: {
                fontSize: 12,
            },
            //默认以千分位显示，不想用的可以在这加一段
            axisLabel: {   //调整左侧Y轴刻度， 直接按对应数据显示
                show: true,
                showMinLabel: true,
                showMaxLabel: true,
                formatter: function (value) {
                    return value;
                }
            }
        },
        {
            type: 'value',
            name: "订\n单\n个\n数\n︵\n个\n︶",
            nameLocation: "center",
            nameGap: 35,
            nameRotate: 0,
            nameTextStyle: {
                fontSize: 12,
            },
            //默认以千分位显示，不想用的可以在这加一段
            axisLabel: {   //调整左侧Y轴刻度， 直接按对应数据显示
                show: true,
                showMinLabel: true,
                showMaxLabel: true,
                formatter: function (value) {
                    return value;
                }
            }
        }
    ],
    series: [
        {
            name: '订单金额',
            type: 'line',
            smooth: true,
            yAxisIndex: 0,
            data: [],
            itemStyle: {normal: {label: {show: true}}},
        },
        {
            name: '订单数量',
            type: 'line',
            smooth: true,
            yAxisIndex: 1,
            data: [],
            itemStyle: {normal: {label: {show: true}}},
        }
    ]
};

// 新增用户统计
var userOption = {
    title: {
        text: '每日新增用户统计'
    },
    tooltip: {
        trigger: 'axis',
        axisPointer: {
            type: 'cross',
            label: {
                backgroundColor: '#6a7985'
            }
        }
    },
    toolbox: {
        show: true,
        top: 10,
        right: 10,
        feature: {
            mark: {show: true},
            magicType: {show: true, type: ['line', 'bar']},
            restore: {show: true},
            saveAsImage: {show: true}
        }
    },
    legend: {
        data: ['新增用户']
    },
    grid: {
        left: '3%',
        right: '4%',
        bottom: '3%',
        containLabel: true
    },
    xAxis: [
        {
            type: 'category',
            boundaryGap: false,
            data: []
        }
    ],
    yAxis: {
        type: 'value'
    },
    series: [{
        name: '新增用户',
        data: [],
        type: 'line',
        smooth: true,
        areaStyle: {}
    }]
};

// 初始化商品介绍富文本编辑器组件
var initProdIntroductionEditor = function (editorId, data) {
    CKEDITOR.replace(editorId, {
        toolbar: null,
        toolbarGroups: null,
        removeButtons: null,
        height: 100
    });
    CKEDITOR.config.extraPlugins = 'selectall,notification,notificationaggregator,widgetselection,filetools,lineutils,widget,uploadwidget,uploadimage';
    // 其它配置项
    var uploadUrl = ctx + "ajax/upload/image/batch";
    CKEDITOR.config.filebrowserImageUploadUrl = uploadUrl; // 图片上传路径
    CKEDITOR.config.removeDialogTabs = 'image:advanced;image:Link'; // 移除图片上传页面的'高级','链接'页签
    // CKEDITOR.config.removePlugins = 'elementspath,resize'; // 移除编辑器底部状态栏显示的元素路径和调整编辑器大小的按钮
    CKEDITOR.config.uploadImgSupportedTypes = '/image\\/(jpeg|png|gif|bmp)/';  // 上传图片格式限制
    CKEDITOR.config.image_previewText = "";

    // CKEDITOR.on('instanceReady', function (evt) {
    //     var editor = evt.editor;
    //     editor.setData(data);
    // });
}

//建立一個可存取到該file的url
var getObjectURL = function (file) {
    var url = null;
    // 下面函数执行的效果是一样的，只是需要针对不同的浏览器执行不同的 js 函数而已
    if (window.createObjectURL != undefined) { // basic
        url = window.createObjectURL(file);
    } else if (window.URL != undefined) { // mozilla(firefox)
        url = window.URL.createObjectURL(file);
    } else if (window.webkitURL != undefined) { // webkit or chrome
        url = window.webkitURL.createObjectURL(file);
    }
    return url;
}






