var context, form;

function makeEditable(ctx) {
    context = ctx;
    form = $('#detailsForm');
    $(".delete").click(function () {
        if (confirm('Are you sure?')) {
            deleteRow($(this).attr("id"));
        }
    });

    $(document).ajaxError(function (event, jqXHR, options, jsExc) {
        failNoty(jqXHR);
    });

    // solve problem with cache in IE: https://stackoverflow.com/a/4303862/548473
    $.ajaxSetup({cache: false});
}

function add() {
    form.find(":input").val("");
    $("#editRow").modal();
    successNoty("Deleted");
}

function deleteRow(id) {
    $.ajax({
        url: context.ajaxUrl + id,
        type: "DELETE"
    }).done(function () {
        updateTable();
    });
}

function updateTable() {
    $.get(context.ajaxUrl, function (data) {
        context.datatableApi.clear().rows.add(data).draw();
        successNoty("Saved");
    });
}

let failedNote;

function closeNoty() {
    if (failedNote) {
        failedNote.close();
        failedNote = undefined;
    }
}

function successNoty(text) {
    closeNoty();
    new Noty({
        text: "<span class='fa fa-lg fa-check'></span> &nbsp;" + text,
        type: 'success',
        layout: "bottomRight",
        timeout: 1000
    }).show();
}

function failNoty(jqXHR) {
    closeNoty();
    failedNote = new Noty({
        text: "<span class='fa fa-lg fa-exclamation-circle'></span> &nbsp;Error status: " + jqXHR.status,
        type: "error",
        layout: "bottomRight"
    }).show();
}

function save() {
    var form = $("#detailsForm");
    $.ajax({
        type: "POST",
        url: context.ajaxUrl,
        data: form.serialize()
    }).done(function () {
        $("#editRow").modal("hide");
        updateTable();
    });
}