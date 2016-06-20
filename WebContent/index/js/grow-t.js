var value0 = [];  //语文的学生的年级  用来放进echart里面的数据
	var value1 = [];  //语文的学生的平均pr值
	
	var value2 = [];//数学的学生的年级
	var value3 = [];//数学的学生的平均pr值
	
	var value4 = [];//英语的学生的年级
	var value5 = [];//英语的学生的平均pr值
    function pic(){
    	// 语文   路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/line',
				'echarts/chart/bar'// 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('mainchinese')); 
                
                var option = {
                    title : {
        text: '语文',
        subtext: ''
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['学生各阶段学习PR值']
    },
    toolbox: {
        show : true,
        feature : {
            //mark : {show: true},
            //dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            //saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ['一年级上','一年级下','二年级上','二年级下','三年级上','三年级下','四年级上','四年级下','五年级上','五年级下','六年级上','六年级下']
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value} '
            }
        }
    ],
    series : [
        {
            name:'学生各阶段学习PR值',
            type:'line',
            data:[81, 51, 75, 63, 92, 83, 70, 71, 88, 75, 60, 55, 77, 79],
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        }
    ]
                };
              //为语文折线图赋值
    			/* var value0 = [];
    			var value1 = [];
    			
    			<c:forEach items="${chineseList}" var="studentPr">
    				value0.push("${studentPr.classyear}");
    			</c:forEach>
    			<c:forEach items="${chineseList}" var="studentPr">
    				value1.push("${studentPr.avgPR}");
    			</c:forEach> */
    						
    			option['xAxis'][0]['data'] = value0;
    			option['series'][0]['data']= value1;
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );

		// 数学   路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/line',
				'echarts/chart/bar'// 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('mainmath')); 
                
                var option = {
                    title : {
        text: '数学',
        subtext: ''
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['学生各阶段学习PR值']
    },
    toolbox: {
        show : true,
        feature : {
            //mark : {show: true},
            //dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            //saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : ['一年级上','一年级下','二年级上','二年级下','三年级上','三年级下','四年级上','四年级下','五年级上','五年级下','六年级上','六年级下']
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value} '
            }
        }
    ],
    series : [
        {
            name:'学生各阶段学习PR值',
            type:'line',
            data:[81, 50, 75, 63, 96, 83, 70, 57, 88, 75, 90, 55, 77, 79],
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        }
    ]
                };
              //为图赋值
    			/* var value2 = [];
    			var value3 = [];
    			<c:forEach items="${mathList}" var="studentPr">
    				value2.push("${studentPr.classyear}");
    			</c:forEach>
    			<c:forEach items="${mathList}" var="studentPr">
    				value3.push("${studentPr.avgPR}");
    			</c:forEach> */
    						
    			option['xAxis'][0]['data'] = value2;
    			option['series'][0]['data']= value3;
        
                // 为echarts对象加载数据 
                myChart.setOption(option); 
            }
        );

		// 英语   路径配置
        require.config({
            paths: {
                echarts: 'http://echarts.baidu.com/build/dist'
            }
        });
        
        // 使用
        require(
            [
                'echarts',
                'echarts/chart/line',
				'echarts/chart/bar'// 使用柱状图就加载bar模块，按需加载
            ],
            function (ec) {
                // 基于准备好的dom，初始化echarts图表
                var myChart = ec.init(document.getElementById('mainenglish')); 
                
                var option = {
                    title : {
        text: '英语',
        subtext: ''
    },
    tooltip : {
        trigger: 'axis'
    },
    legend: {
        data:['学生各阶段学习PR值']
    },
    toolbox: {
        show : true,
        feature : {
            //mark : {show: true},
            //dataView : {show: true, readOnly: false},
            magicType : {show: true, type: ['line', 'bar']},
            restore : {show: true},
            //saveAsImage : {show: true}
        }
    },
    calculable : true,
    xAxis : [
        {
            type : 'category',
            boundaryGap : false,
            data : value4
        }
    ],
    yAxis : [
        {
            type : 'value',
            axisLabel : {
                formatter: '{value} '
            }
        }
    ],
    series : [
        {
            name:'学生各阶段学习PR值',
            type:'line',
            data:value5,
            markPoint : {
                data : [
                    {type : 'max', name: '最大值'},
                    {type : 'min', name: '最小值'}
                ]
            },
            markLine : {
                data : [
                    {type : 'average', name: '平均值'}
                ]
            }
        }
    ]
                };
           
                myChart.setOption(option); 
            }
        );
    }

		