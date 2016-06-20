$(function() {   

    Morris.Bar({
        element: 'morris-bar-chart',
        data: [{
            y: '100~90',
            a: 5
        }, {
            y: '90~80',
            a: 8
        }, {
            y: '80~70',
            a: 14
        }, {
            y: '70~60',
            a: 13
        }, {
            y: '< 60',
            a: 6
        }],
        xkey: 'y',
        ykeys: 'a',
        labels: ['人数'],
        hideHover: 'auto',
        resize: true
    });

});
