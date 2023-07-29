

var objProcess
function showMessageError(msg) {
    $(".alert-danger").removeClass("hide")
    $(".alert-danger").addClass("show")
    $(".alert-danger span.text").html(msg)
    setTimeout(removeMessage, 7000)
    //alert-success
}

function showMessageSuccess(msg) {
    $(".alert-success").removeClass("hide")
    $(".alert-success").addClass("show")
    $(".alert-success span.text").html(msg)
    setTimeout(removeMessage, 7000)
    //
}
function removeMessage(){
    $(".alert").addClass("hide")
    $(".alert").removeClass("show")
}

$(".close").click(function (){
    $(".alert").addClass("hide")
    $(".alert").removeClass("show")
});

function getCrawlerProcess(id) {
    console.log("getCrawlerProcess");

    var jqxhr = $.get( "/crawler/"+id, function() {

    }).done(function(data) {

        loadProcessInput(data)
        $("#title").html("Cadastro")
        $("#submit").attr("value", "Cadastrar")

    })
    .fail(function(err) {
        load()
        if (err.status === 404){
            showMessageError( "N&atildeo foi possivel encontrar o processo '"+id+"'" );
        }else{
            showMessageError( "Ocorreu um erro de comunica&ccedil;&atilde;o com esse servi&ccedil;o, por favor tente mais tarde" );
        }

    });

    return false;

}

function loadProcessInput(data) {
    load()
    toggle()
    objProcess = data;
    $("#input_title").val(objProcess.title)
    $("#input_pub").val(objProcess.externalIdProcess)
    $("#input_internacional_order").val(objProcess.internationalOrder)
    $("#input_publication_date").val(objProcess.publicationDate)
    $("#input_claimant").val(objProcess.claimant)
    $("#back").toggle()

    $("#submit").attr("data-step", "2")
}

function getInternalProcess(id) {
    console.log("getInternalProcess");

    var jqxhr = $.get( "/process/search/"+id, function() {
        //showMessageSuccess( "success" );
    }).done(function(data) {
            if (data.length === 0){
                getCrawlerProcess(id)
            }else {
                $("#submit").attr("value", "Processo ja cadastrado")
                $("#submit").attr("disabled", true)
                loadProcessInput(data[0])
            }
        })
        .fail(function() {
            load()
            showMessageError( "Ocorreu um erro de comunica&ccedil;&atilde;o com esse servi&ccedil;o, por favor tente mais tarde" );

        });

    return false;

}

function postProcess() {

    var jqxhr = $.post({
        url: "/process",
        contentType: "application/json",
        dataType: "json",
    }, JSON.stringify(objProcess), function() {
    })
        .done(function(data) {
            load()
            toggle()
            $("#back").toggle()
            console.log(data)
            $("#title").html("Pesquisa")
            $("#submit").attr("value","Pesquisar")
            $("#submit").attr("data-step","1")
            showMessageSuccess( "Processo Cadastro com Sucesso!" );

        })
        .fail(function() {
            //$("#back").toggle()
            load()
            showMessageError( "Ocorreu um erro de comunica&ccedil;&atilde;o com esse servi&ccedil;o, por favor tente mais tarde" );

        })
        .always(function() {},"json");

    return false;

}
function toggle(){
    $("#form-search").slideToggle(500)
    $("#form-register").slideToggle(500)
}

function load(){
    $("#load").slideToggle(500)
}


$("#back").click(function() {
    $("#title").html("Pesquisa")
    $("#submit").attr("value","Pesquisar")
    $("#submit").attr("data-step","1")
    $("#back").toggle()
    $("#submit").attr("disabled", false)
    toggle();
});

$("#form").submit(function() {
    load();
    var dataStep = $("#submit").attr("data-step")
    if (dataStep == "1"){
        var termSearch = $("#term_search").val()
        getInternalProcess(termSearch)
    }
    if (dataStep == "2"){
        postProcess()
    }
    return false;
});



//===========================================================

let tr = ""
function foreachProcess(objProcess) {
    console.log(objProcess)
    let tbody = $("#table-list tbody")

    tbody.html("")
    tbody.slideToggle(500)

    for (let objProcessKey in objProcess) {

        tr = '<tr>'
        tr +='<th class="text-truncate" data-toggle="tooltip" data-placement="top" title="'+objProcess[objProcessKey].externalIdProcess+'"  scope="row"><span class="d-inline-block text-truncate" style="max-width: 150px;">'+objProcess[objProcessKey].externalIdProcess+'</span></th>'
        tr +='<td class="text-truncate" data-toggle="tooltip" data-placement="top" title="'+objProcess[objProcessKey].title+'"> <span class="d-inline-block text-truncate" style="max-width: 150px;">'+objProcess[objProcessKey].title+'</span></td>'
        tr +='<td class="text-truncate" data-toggle="tooltip" data-placement="top" title="'+objProcess[objProcessKey].internationalOrder+'"> <span class="d-inline-block text-truncate" style="max-width: 150px;">'+objProcess[objProcessKey].internationalOrder+'</span></td>'
        tr +='<td class="text-truncate" data-toggle="tooltip" data-placement="top" title="'+objProcess[objProcessKey].claimant+'"> <span class="d-inline-block text-truncate" style="max-width: 150px;">'+objProcess[objProcessKey].claimant+'</span></td>'
        tr +='<td class="text-truncate" data-toggle="tooltip" data-placement="top" title="'+objProcess[objProcessKey].publicationDate+'"> <span class="d-inline-block text-truncate" style="max-width: 150px;">'+objProcess[objProcessKey].publicationDate+'</span></td>'
        tr +='</tr>'

        $("#table-list tbody").append(tr)
    }

    tbody.slideToggle(500)
}

function getProcessAll() {

    let jqxhr = $.get( "/process", function() {
        console.log( "success" );
    }).done(function(objProcess) {
        foreachProcess(objProcess)
    })
        .fail(function() {
            console.log( "error" );
        })
        .always(function() {
            console.log( "finished" );
        });

    jqxhr.always(function() {
        loadInternal()
    });
    return false;

}
function getProcessDatabase(term) {

    let jqxhr = $.get( "/process/search/"+term, function() {

    }).done(function(objProcess) {
        if (objProcess.length == 0){
            showMessageError( "N&atildeo foi possivel encontrar o processo '"+term+"'" );
        }else{
            foreachProcess(objProcess)
        }
    })
    .fail(function() {
        showMessageError( "Ocorreu um erro de comunica&ccedil;&atilde;o com esse servi&ccedil;o, por favor tente mais tarde" );
    });

    jqxhr.always(function() {
        loadInternal()
    });
    return false;

}

function loadInternal(){
    $("#load").slideToggle(500)
    $("#table-list").slideToggle(500)
}



$("#form-internal").submit(function() {
    loadInternal();
    let termSearch = $("#term_search").val()
    if (termSearch.length === 0){
        getProcessAll()
    }else{
        getProcessDatabase(termSearch)
    }
    return false;
});
