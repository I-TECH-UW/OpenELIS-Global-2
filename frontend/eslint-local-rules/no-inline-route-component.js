/**
 * ESLint rule: no-inline-route-component
 *
 * Bans an inline arrow as a react-router `component` prop; use `render`.
 *
 * react-router renders the `component` prop with `React.createElement`, so an
 * inline arrow is a new component TYPE on every render of whatever built the
 * route element. React then unmounts the old subtree and mounts a fresh one,
 * losing every piece of the page's state. `render` calls the function instead,
 * so the element type stays whatever the arrow returns and React reconciles.
 *
 * This cost the WHONET export page its filters: App.jsx builds its route table
 * inside a parent `render` callback, so every `history.replace` the page made
 * to put its own filters in the query string rebuilt the route table, remounted
 * the page, and reset the filters it had just written. It surfaced as a block
 * of microbiology E2E failures, not as anything visible in a unit test.
 */
export default {
  meta: {
    type: "problem",
    docs: {
      description:
        "Require `render` in place of an inline arrow `component` prop on a " +
        "route, which remounts the page on every parent render.",
    },
    schema: [],
    messages: {
      useRender:
        "Use `render={() => ...}` instead of `component={() => ...}` on a " +
        "route. react-router calls React.createElement on `component`, so an " +
        "inline arrow is a new component type every render and React remounts " +
        "the page, discarding its state.",
    },
  },
  create(context) {
    return {
      JSXAttribute(node) {
        if (node.name?.type !== "JSXIdentifier") return;
        if (node.name.name !== "component") return;
        const value = node.value;
        if (value?.type !== "JSXExpressionContainer") return;
        const expression = value.expression;
        if (
          expression?.type === "ArrowFunctionExpression" ||
          expression?.type === "FunctionExpression"
        ) {
          context.report({ node, messageId: "useRender" });
        }
      },
    };
  },
};
