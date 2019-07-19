<!DOCTYPE html>
<html>
   <head>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
      <style>
         table, th, td {
         border: 1px solid black;
         border-collapse: collapse;
         }
         th, td {
         padding: 5px;
         text-align: left;
         }
         div.a {
         font-weight: bold;
         padding: 5px;
         text-align: center;
         }
         .temp {
         text-align: right;
         }
         div.d {
         padding-left: 10px;
         padding-top: 5px;
         text-align: left;
         padding-top: 5px;
         }
      </style>
      <script
         src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
   </head>
   <body>
      <div class="d">
         <a href="/"  style="text-decoration:none" class="btn btn-primary" role="button">Go Back to Home</a><br>
      </div>
      <div class="a">
         <p>Zonal Wise Collection of Property tax for the year 2014</p>
         <table style="width: 100%" id="tableId">
            <tr>
               <th>Zone Name</th>
               <th>Property Type</th>
               <th>Amount Collected</th>
            </tr>
         </table>
      </div>
   </body>
   <script type="text/javascript">
      $(document).ready(
      		function() {
      
      			$.ajax({
      				url : "/view",
      				datatype : "JSON",
      				type : "Get",
      				cache : false,
      				success : function(data) {
      					var trHTML = '';
      					$.each(data, function(i, item) {
      
      						trHTML += '<tr>' + '<th rowspan="2">'
      								+ item.zoneCategory + '</th>' + '<td>'
      								+ 'Owner' + '</td>' + '<td class="temp">'
      								+ '&#8377; '
      								+ Math.round(item.ownerTotal * 100.0)
      								/ 100.0 + '</td>' + '</tr>' + '<tr>'
      								+ '<td>' + 'Tenanted' + '</td>'
      								+ '<td class="temp">' + '&#8377; '
      								+ Math.round(item.tenantTotal * 100.0)
      								/ 100.0 + '</td>' + '</tr>';
      
      					});
      					$('#tableId').append(trHTML);
      				},
      
      			});
      
      		});
   </script>
</html>