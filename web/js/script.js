$(document).ready(function () {

  fetchAll();
  deletePerson();
  initPersons();
  initAddBtn();
  initCancelBtn();
  initSaveBtn();

});


function hideError() {
  $("#err").text("").hide();
}

function initAddBtn() {
  hideError()
  $("#btn_add").click(function () {
    initDetails(true);
    fetchAll();
  });
}

function initSaveBtn() {
  $("#btn_save").click(function () {
    hideError();
    $("#err").text("");
    //First create post argument as a JavaScript object
    var newPerson = {fName: $("#fname").val(), lName: $("#lname").val(), phone: $("#phone").val()};
    $.ajax({
      url: "api/person",
      data: JSON.stringify(newPerson), //Convert newPerson to JSON
      type: "post",
      contentType: "application/json; charset=utf-8",
      dataType: 'JSON',
      error: function (jqXHR, textStatus, errorThrown) {
        $("#err").text(jqXHR.responseJSON.message).show();
      }
    }).done(function (newPerson) {
      $("#id").val(newPerson.id);
      initDetails(false);
      fetchAll();
    });
  });
}

function initCancelBtn() {
  $("#btn_cancel").click(function () {
    hideError();
    clearDetails();
    initDetails(false);
    fetchAll();
  });
}


function initPersons() {
  $("#persons").click(function (e) {
    hideError();
    var id = e.target.id;
    if (isNaN(id)) {
      return;
    }
    updateDetails(id);
  });
}

function deletePerson() {
  $("#delete").click(function () {
    hideError();
    $.ajax({
      url: "api/person/" + $("#persons option:selected").attr("id"),
      type: "DELETE",
      dataType: 'json',
      error: function (jqXHR, textStatus, errorThrown) {
        $("#err").text(jqXHR.responseJSON.message).show();
      }
    }).done(function () {
      fetchAll();
    });
  });
}

function initDetails(init) {
  if (init) {
    $("#fname").removeAttr("disabled");
    $("#lname").removeAttr("disabled");
    $("#phone").removeAttr("disabled");
    $("#btn_save").removeAttr("disabled");
    $("#btn_cancel").removeAttr("disabled");
    $("#btn_add").attr("disabled", "disabled");
  }
  else {
    $("#fname").attr("disabled", "disabled");
    $("#lname").attr("disabled", "disabled");
    $("#phone").attr("disabled", "dsiabled");
    $("#btn_save").attr("disabled", "disabled");
    $("#btn_cancel").attr("disabled", "disabled");
    $("#btn_add").removeAttr("disabled");
  }
}

function clearDetails() {
  $("#id").val("");
  $("#fname").val("");
  $("#lname").val("");
  $("#phone").val("");
}
function updateDetails(id) {
  hideError();
  $.ajax({
    url: "api/person/" + id,
    type: "GET",
    dataType: 'json',
    error: function (jqXHR, textStatus, errorThrown) {
      $("#err").text(jqXHR.responseText + ": " + textStatus).show();
    }
  }).done(function (person) {
    $("#id").val(person.id);
    $("#fname").val(person.fName);
    $("#lname").val(person.lName);
    $("#phone").val(person.phone);
  });
}

function fetchAll() {
  hideError();
  $.ajax({
    url: "api/person",
    type: "GET",
    dataType: 'json',
    error: function (jqXHR, textStatus, errorThrown) {
      $("#err").text(jqXHR.responseText + ": " + textStatus).show();
    }
  }).done(function (persons) {
    var options = "";
    persons.forEach(function (person) {
      options += "<option id=" + person.id + ">" + person.fName[0] + ", " + person.lName + "</option>";
    });
    $("#persons").html(options);
    clearDetails();
  });
}