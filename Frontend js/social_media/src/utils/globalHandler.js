export function decodeToken() {
  debugger
  const tokenString = localStorage.getItem('token');
  if (tokenString === null)
    return null;
  else
    return JSON.parse(decodeURIComponent(atob(tokenString)));
}

export function getCurrentUserEmailAddress() {
  var token = decodeToken();
  if (token != null)
    return token.email;
  else
    return "";
}