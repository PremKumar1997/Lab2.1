<!DOCTYPE html>
<html>
   <head>
      <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/css/bootstrap.min.css">
      <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.4.1/jquery.min.js"></script>
      <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.0/js/bootstrap.min.js"></script>
      <style>
         div.home {
         height:auto; width:500px;
         }
         div.form{
         border-style: solid;
         border-color: coral;
         height:auto; width:700px;
         margin: auto;
         background: white;
         padding: 20px;
         padding-top: 10px;
         }
         .textarea {
         height: 40px;
         width: 200px;
         overflow-y: scroll;
         }
         .paddingBottom{
         padding-bottom: 10px;
         }
         .center {
         margin: auto;
         width: 50%;
         padding: 10px;
         }
      </style>
      <script
         src="https://ajax.googleapis.com/ajax/libs/jquery/1.10.1/jquery.min.js"></script>
   </head>
   <body style="padding-top: 20px;padding-left: 50px;">
      <div class="form">
         <h3 style="font-weight: bold;">SelfAssessment of Property Tax Form</h3>
         <form id="payment">
            <table>
               <tbody>
                  <tr>
                     <td class="paddingBottom" width="200">Year of Assessment</td>
                     <td class="paddingBottom"><input name="yearOfAssessment" type="text"
                        id="assessmentYear" onblur="yearValidation(this.value,event)">
                        <br>
                     </td>
                  </tr>
                  <tr >
                     <td class="paddingBottom">Name Of the Owner</td>
                     <td class="paddingBottom"> <input name="owner" type="text"> <br></td>
                  </tr>
                  <tr>
                     <td class="paddingBottom">Email</td>
                     <td class="paddingBottom"><input name="email" type="text"> <br></td>
                  </tr>
                  <tr>
                     <td class="paddingBottom">Address of Property</td>
                     <td class="paddingBottom"><textarea name="address" class="textarea"></textarea> <br></td>
                  </tr>
                  <tr>
                     <td class="paddingBottom">Zonal classification</td>
                     <td>
                        <select id="zoneId" onchange="getResidenceType()">
                           <option value="select">--select--</option>
                        </select>
                        <br>
                     </td>
                  </tr>
                  <tr>
                     <td class="paddingBottom">Description of the property</td>
                     <td>
                        <select id="residenceType" onchange="remove()">
                           <option value="value">--select--</option>
                        </select>
                        <br>
                     </td>
                  </tr>
                  <tr>
                     <td class="paddingBottom">status</td>
                     <td class="paddingBottom">
                        <select id="status" onchange="remove()">
                           <option value="Owner">Owner</option>
                           <option value="Tenanted">Tenant</option>
                        </select>
                        <br>
                     </td>
                  </tr>
                  <tr>
                     <td class="paddingBottom">Building constructed year</td>
                     <td class="paddingBottom"><input id="yearId" type="text" onchange="remove()"
                        onblur="yearValidation(this.value,event)"> <br></td>
                  </tr>
                  <tr>
                     <td class="paddingBottom">Build Up Area (Square feet)</td>
                     <td class="paddingBottom"><input id="areaId" type="text" onchange="remove()"
                        oninput="this.value = this.value.replace(/[^0-9.]/g, '').replace(/(\..*)\./g, '$1');">
                        <br>
                     </td>
                  </tr>
                  <tr>
                     <td class="paddingBottom">Total payable tax</td>
                     <td class="paddingBottom"style="width: 550px;" >
                        <input name="area" type="text" readonly="readonly"
                           id="totalId" onfocusin="residentialTaxCalculation(this)"> [Computation form the details provided above]<br>
                     </td>
                  </tr>
                  <tr>
                     <td><a href="/"  style="text-decoration:none;" class="btn btn-primary" role="button">cancel</a></td>
                     <td><input class="btn btn-primary" role="button" type="submit" value="Pay Tax"
                        disabled="disabled" id="btnSubmit"></td>
                  </tr>
               </tbody>
            </table>
         </form>
      </div>
   </body>
   <script type="text/javascript">
      $(document).ready(
      		function() {
      			$("#totalId").val('');
      			$.ajax({
      				url : "/all",
      				datatype : "JSON",
      				type : "Get",
      				cache : false,
      				success : function(data) {
      					$.each(data, function(i, item) {
      						$('#zoneId').append(
      								'<option value="' + data[i] + '">'
      										+ data[i] + '</option>');
      					});
      
      				},
      
      			});
      
      		});
      
      function remove() {
      	$("#totalId").val('');
      }
      
      function getResidenceType() {
      	var pathParam = document.getElementById("zoneId").value;
      	$("#totalId").val('');
      	$("#residenceType").empty();
      	$("#residenceType").val('--select--');
      	if (null != pathParam && pathParam == 'select') {
      		var x = document.getElementById("residenceType");
      		var option = document.createElement("option");
      		option.text = "--select--";
      		x.add(option);
      	}
      	if (null != pathParam && pathParam != 'select') {
      		$.ajax({
      			url : "/all/" + pathParam,
      			datatype : "JSON",
      			type : "Get",
      			cache : false,
      			success : function(data) {
      
      				$.each(data, function(i, item) {
      
      					$('#residenceType').append(
      							'<option value="' + data[i] + '">' + data[i]
      									+ '</option>');
      				});
      			},
      		});
      	}
      }
      
      function yearValidation(year, event) {
      	var text = /^[0-9]+$/;
      	if (event.type == "blur" || year.length == 4 && event.keyCode != 8
      			&& event.keyCode != 46) {
      		if (year != 0) {
      			if ((year != "") && (!text.test(year))) {
      
      				alert("Please Enter Numeric Values Only");
      				$("#assessmentYear").val('');
      				return false;
      			}
      			if (year.length != 4) {
      				alert("Year is not proper. Please check");
      				$("#assessmentYear").val('');
      				return false;
      			}
      			var current_year = new Date().getFullYear();
      			if (year > current_year) {
      				alert("Year should not be greater than current year");
      				$("#assessmentYear").val('');
      				return false;
      			}
      			return true;
      		}
      	}
      }
      
      function residentialTaxCalculation(x) {
      	var formValueObject = {};
      	formValueObject["zoneId"] = document.getElementById("zoneId").value;
      	formValueObject["yearId"] = document.getElementById("yearId").value;
      	formValueObject["areaId"] = document.getElementById("areaId").value;
      	formValueObject["propertyId"] = document.getElementById("residenceType").value;
      	formValueObject["status"] = document.getElementById("status").value;
      if (document.getElementById("yearId").value != ''
      			&&  document.getElementById("areaId").value != '') {
      		$.ajax({
      			url : "/taxValue",
      			datatype : "JSON",
      			type : "POST",
      			cache : false,
      			data : JSON.stringify(formValueObject),
      			contentType : "application/json; charset=utf-8",
      
      			success : function(data) {
      				if (null != data && data != 0) {
      					$("#btnSubmit").removeAttr("disabled");
      				}
      				$("#totalId").val(data);
      			},
      		});
      	}
      }
      
      $('form#payment').submit(function(event) {
      	event.preventDefault();
      	var pay = {};
      	pay["zoneId"] = document.getElementById("zoneId").value;
      	pay["propertyId"] = document.getElementById("residenceType").value;
      	pay["yearId"] = document.getElementById("yearId").value;
      	pay["areaId"] = document.getElementById("areaId").value;
      	pay["totalId"] = document.getElementById("totalId").value;
      	pay["status"] = document.getElementById("status").value;
      	
      	$.ajax({
      		url : "/pay",
      		datatype : "JSON",
      		type : "POST",
      		cache : false,
      		data : JSON.stringify(pay),
      		contentType : "application/json; charset=utf-8",
      		success : function(data) { 
      			alert(data)
      			window.location.href = "http://localhost:8080/";
      		},
      		error:function(e){
      		console.log(e)
      		alert(e.responseJSON.message)
      		},
      	});
      });
   </script>