#!/bin/bash

set -e
set -o pipefail
set -u

THIS_PATH="$(readlink --canonicalize-existing "${0}")"
THIS_NAME="$(basename "${THIS_PATH}")"
THIS_DIR="$(dirname "${THIS_PATH}")"

ROOT="${THIS_DIR}/../.."

# checkout openjdk
JDK_DIR="${ROOT}/target/openjdk"
if [ ! -d "${JDK_DIR}" ]; then
    mkdir --parents                                                                 "${ROOT}/target"
    git clone --depth=1 --branch=jdk8u/jdk8u https://github.com/dmlloyd/openjdk.git "${JDK_DIR}"
fi

# copy sources
rm --recursive --force                                        "${ROOT}/src/main/java"
mkdir --parents                                               "${ROOT}/src/main/java/de/shadowhunt/javascript"
cp --recursive ${JDK_DIR}/nashorn/src/jdk/*                   "${ROOT}/src/main/java/de/shadowhunt/javascript"
cp --recursive ${JDK_DIR}/nashorn/buildtools/nasgen/src/jdk/* "${ROOT}/src/main/java/de/shadowhunt/javascript"

# copy resources
rm --recursive --force                                        "${ROOT}/src/main/resources"
for i in `find "${ROOT}/src/main/java" -type f | grep -v java$`; do
    folder=`dirname "${i}" | sed "s|${ROOT}/src/main/java|${ROOT}/src/main/resources|g"`
    mkdir --parents "${folder}"
    mv "${i}" "${folder}"
done

# clean empty folders
for i in `find "${ROOT}/src/main/java" -type d`; do
    rmdir --ignore-fail-on-non-empty $i
done

# patch source to use correct references
for i in `find "${ROOT}/src/main" -type f`; do
    sed --in-place 's/jdk\.internal\.org\.objectweb/org.objectweb/g' "${i}"

    sed --in-place 's/@implSpec//g'                                  "${i}"
    sed --in-place 's/@jdk\.Exported//g'                             "${i}"
    sed --in-place 's/jdk\./de.shadowhunt.javascript./g'         "${i}"
    sed --in-place 's|jdk/|de/shadowhunt/javascript/|g'          "${i}"
done

# convert files to unix
find "${ROOT}/src/main" -type f | xargs dos2unix

# add custom extensions
sed --in-place 's/^}/public ScriptObject getInternalScriptObject() { return sobj; } }/g' "${ROOT}/src/main/java/de/shadowhunt/javascript/nashorn/api/scripting/ScriptObjectMirror.java"
sed --in-place 's/^}/public double getInternalTime() { return time; } }/g'               "${ROOT}/src/main/java/de/shadowhunt/javascript/nashorn/internal/objects/NativeDate.java"
