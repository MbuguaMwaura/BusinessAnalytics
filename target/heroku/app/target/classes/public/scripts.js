  document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.sidenav');
    var instances = M.Sidenav.init(elems);
  });

   document.addEventListener('DOMContentLoaded', function() {
      var elems = document.querySelectorAll('.dropdown-trigger');
      var instances = M.Dropdown.init(elems);
    });


  document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.tooltipped');
    var instances = M.Tooltip.init(elems);
  });

 document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('.datepicker');
    var instances = M.Datepicker.init(elems);
  });


  document.addEventListener('DOMContentLoaded', function() {
    var elems = document.querySelectorAll('select');
    var instances = M.FormSelect.init(elems);
  });


//  document.addEventListener('DOMContentLoaded', function() {
//    var elems = document.querySelectorAll('.scrollspy');
//    var instances = M.ScrollSpy.init(elems);
//  });
//
//
//var ctx = document.getElementById('myChart').getContext('2d');
//var myChart = new Chart(ctx, {
//    type: 'bar',
//    data: {
//        labels: ['Red', 'Blue', 'Yellow', 'Green', 'Purple', 'Orange'],
//        datasets: [{
//            label: '# of Votes',
//            data: [12, 19, 3, 5, 2, 3],
//            backgroundColor: [
//                'rgba(255, 99, 132, 0.2)',
//                'rgba(54, 162, 235, 0.2)',
//                'rgba(255, 206, 86, 0.2)',
//                'rgba(75, 192, 192, 0.2)',
//                'rgba(153, 102, 255, 0.2)',
//                'rgba(255, 159, 64, 0.2)'
//            ],
//            borderColor: [
//                'rgba(255, 99, 132, 1)',
//                'rgba(54, 162, 235, 1)',
//                'rgba(255, 206, 86, 1)',
//                'rgba(75, 192, 192, 1)',
//                'rgba(153, 102, 255, 1)',
//                'rgba(255, 159, 64, 1)'
//            ],
//            borderWidth: 1
//        }]
//    },
//    options: {
//        scales: {
//            yAxes: [{
//                ticks: {
//                    beginAtZero: true
//                }
//            }]
//        }
//    }
//});


var ctx = document.getElementById('myChart').getContext('2d');
var v1 = parseInt(document.getElementById("chartdata").value);
var v2 = parseInt(document.getElementById("chartdata2").value);
var v3 = parseInt(document.getElementById("chartdata3").value);
var v4 = parseInt(document.getElementById("chartdata4").value);
console.log(v1)
var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'horizontalBar',

    // The data for our dataset
    data: {
    labels: ['Incomes', 'Expenses','Bills','Receipts'],

        datasets: [{
            label: 'CashFlows',
            data: [v1, v2,v3,v4],
              backgroundColor: [
                            'rgba(233,114,77, 0.2)',
                            'rgba(54, 162, 235, 0.2)',
                            'rgba(255, 206, 86, 0.2)',
                            'rgba(75, 192, 192, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)',
                            'rgba(255, 206, 86, 1)',
                            'rgba(75, 192, 192, 1)'
                        ],
                        borderWidth: 1

        }]
    },
    options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
});

var ctx = document.getElementById('myChart2').getContext('2d');
var v1 = parseInt(document.getElementById("chartdata5").value);
var v2 = parseInt(document.getElementById("chartdata6").value);

console.log(v1)
var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'doughnut',

    // The data for our dataset
    data: {
    labels: ['Incomes', 'Bills'],

        datasets: [{
            label: 'Dues',
            data: [v1, v2],
              backgroundColor: [
                            'rgba(233,114,77, 0.2)',
                            'rgba(54, 162, 235, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)'
                        ],
                        borderWidth: 1

        }]
    },
    options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
});

var ctx = document.getElementById('myChart3').getContext('2d');
var v1 = parseInt(document.getElementById("chartdata7").value);
var v2 = parseInt(document.getElementById("chartdata8").value);

console.log(v1)
var chart = new Chart(ctx, {
    // The type of chart we want to create
    type: 'pie',

    // The data for our dataset
    data: {
    labels: ['Incomes', 'Expenses '],

        datasets: [{
            label: 'Income V Expense',
            data: [v1, v2],
              backgroundColor: [
                            'rgba(233,114,77, 0.2)',
                            'rgba(54, 162, 235, 0.2)'
                        ],
                        borderColor: [
                            'rgba(255, 99, 132, 1)',
                            'rgba(54, 162, 235, 1)'
                        ],
                        borderWidth: 1

        }]
    },
    options: {
            scales: {
                yAxes: [{
                    ticks: {
                        beginAtZero: true
                    }
                }]
            }
        }
});

