const PathRoute = ({ path, children }) => {
  return window.location.href.endsWith(path) ? children : null;
};

export default PathRoute;
