const { readFileSync } = require("node:fs");

function resolveAnalyzerRuntime(runtime = "plugins") {
  switch (runtime) {
    case "plugins":
      return {
        include_plugin_jars: "true",
        analyzer_projects: "harness-foundational,harness-demo",
        seed_analyzer_traffic: "false",
      };
    case "bridge":
      return {
        include_plugin_jars: "false",
        analyzer_projects: "harness-demo",
        seed_analyzer_traffic: "true",
      };
    default:
      throw new Error(
        `Unsupported analyzer runtime: ${JSON.stringify(runtime)}`,
      );
  }
}

if (require.main === module) {
  let runtime;
  try {
    runtime = readFileSync(process.argv[2], "utf8").trim();
  } catch (error) {
    if (error.code !== "ENOENT") throw error;
  }
  for (const [key, value] of Object.entries(resolveAnalyzerRuntime(runtime))) {
    process.stdout.write(`${key}=${value}\n`);
  }
}

module.exports = { resolveAnalyzerRuntime };
