#!/usr/bin/env sh
#
# Copyright (c) Microsoft Corporation. All rights reserved.
#
VSROOT="$(dirname "$(dirname "$(dirname "$(readlink -f "$0")")")")"
ROOT="$(dirname "$(dirname "$VSROOT")")"

APP_NAME="code-server"
VERSION="1.89.1"
COMMIT="effc6e95b4ad1c5ac5f9083ec06663ba4a2e005c"
EXEC_NAME="code-server"
CLI_SCRIPT="$VSROOT/out/server-cli.js"
"${NODE_EXEC_PATH:-$ROOT/lib/node}" "$CLI_SCRIPT" "$APP_NAME" "$VERSION" "$COMMIT" "$EXEC_NAME" "--openExternal" "$@"
