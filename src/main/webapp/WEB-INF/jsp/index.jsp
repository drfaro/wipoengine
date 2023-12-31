<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<html>
<head>
    <title>Welcome</title>
    <meta charset="utf-8"
          name="viewport"
          content="width=device-width, initial-scale=1.0" />
    <link rel="icon" type="image/x-icon" href="/static/image/favicon.ico">


    <link href="webjars/bootstrap/4.1.2/css/bootstrap.min.css"
          rel="stylesheet">

    <link href="https://use.fontawesome.com/releases/v5.3.1/css/all.css"
          rel="stylesheet">

    <link href="/static/css/style.css" rel="stylesheet">

</head>
<body>
<div id="load" class="hide"></div>
<div class="alert alert-danger alert-dismissible fade hide" role="alert">
    <span class="text"></span>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>
<div class="alert alert-success alert-dismissible fade hide" role="alert">
    <span class="text"> </span>
    <button type="button" class="close" data-dismiss="alert" aria-label="Close">
        <span aria-hidden="true">&times;</span>
    </button>
</div>

<div id="linkTo" class="hide"><a href="/internal"><input type="button" class="btn submit_btn" value="Pesquisar" ></a></div>

<div class="container">
    <div class="d-flex justify-content-center h-100">
        <div class="card">
            <div class="card-header">
                <h3 id="title">Pesquisar</h3>
            </div>
            <div class="card-body">
                <form id="form">
                    <div class="input-group form-group" id="form-search">
                        <div class="input-group-prepend">
                            <span class="input-group-text"><i class="fas fa-search"></i></span>
                        </div>
                        <input type="text" id="term_search" class="form-control" value="WO2002008677" required placeholder="Número de um processo">

                    </div>
                    <div id="form-register" class="hide">
                        <div class="input-group form-group">
                            <input readonly type="text" class="form-control" placeholder="Titulo" id="input_title" />
                        </div>
                        <div class="input-group form-group">
                            <input readonly type="text" class="form-control" placeholder="Número de um processo" id="input_pub" />
                        </div>
                        <div class="input-group form-group">
                            <input readonly type="text" class="form-control" placeholder="Número de pub" id="input_internacional_order" />
                        </div>
                        <div class="input-group form-group">
                            <input readonly type="text" class="form-control" placeholder="Data de publicação" id="input_publication_date" />
                        </div>
                        <div class="input-group form-group">
                            <input readonly type="text" class="form-control" placeholder="Requerentes" id="input_claimant" />
                        </div>
                    </div>

                    <div class="form-group">
                        <input type="button" id="back" value="voltar" class="btn float-left submit_btn hide">
                        <input type="submit" id="submit" data-step="1" value="Pesquisar" class="btn float-right submit_btn">
                    </div>

                </form>
            </div>

        </div>
    </div>
</div>

<script src="${pageContext.request.contextPath}/webjars/jquery/3.5.0/jquery.min.js"></script>
<script src="${pageContext.request.contextPath}/webjars/bootstrap/4.1.2/js/bootstrap.min.js"></script>
<script src="/static/js/util.js"></script>
<script type="application/javascript">
    $(document).ready(function (){
        setTimeout(function (){$("#linkTo").slideToggle(1000)}, 2000)
    })
</script>

</body>
</html>
