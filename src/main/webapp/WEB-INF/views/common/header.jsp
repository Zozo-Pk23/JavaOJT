<nav class="navbar navbar-expand-lg navbar-light bg-light">
  <div class="container">
    <a class="navbar-brand text-success" href="#">Bulletin_Board</a>
    <button class="navbar-toggler" type="button" data-bs-toggle="collapse" data-bs-target="#navbarNav"
      aria-controls="navbarNav" aria-expanded="false" aria-label="Toggle navigation">
      <span class="navbar-toggler-icon"></span>
    </button>
    <div class="collapse navbar-collapse" id="navbarNav">
      <ul class="navbar-nav">
        <li class="nav-item">
          <a class="nav-link text-success" href="${pageContext.request.contextPath}/users/index">Users</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-success" href="${pageContext.request.contextPath}/posts/index">Posts</a>
        </li>
        <li class="nav-item">
          <a class="nav-link text-success" href="${pageContext.request.contextPath}/users/create">Create Users</a>
        </li>
        <li class="nav-item dropdown">
          <a class="nav-link dropdown-toggle text-success" href="#" id="navbarDropdown" role="button"
            data-bs-toggle="dropdown" aria-expanded="false">
            ${sessionScope.user.name}
          </a>
          <ul class="dropdown-menu" aria-labelledby="navbarDropdown">
            <li><a class="dropdown-item text-success" href="${pageContext.request.contextPath}/myprofile">Profile</a></li>
            <li><a class="dropdown-item text-success" href="${pageContext.request.contextPath}/logout">Log Out</a></li>
          </ul>
        </li>
      </ul>
    </div>
  </div>
</nav>