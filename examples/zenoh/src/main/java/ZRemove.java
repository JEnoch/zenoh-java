/*
 * Copyright (c) 2017, 2020 ADLINK Technology Inc.
 *
 * This program and the accompanying materials are made available under the
 * terms of the Eclipse Public License 2.0 which is available at
 * http://www.eclipse.org/legal/epl-2.0, or the Apache License, Version 2.0
 * which is available at https://www.apache.org/licenses/LICENSE-2.0.
 *
 * SPDX-License-Identifier: EPL-2.0 OR Apache-2.0
 *
 * Contributors:
 *   ADLINK zenoh team, <zenoh@adlink-labs.tech>
 */

import org.eclipse.zenoh.*;

import picocli.CommandLine;
import picocli.CommandLine.Option;

public class ZRemove implements Runnable {

    @Option(names = {"-h", "--help"}, usageHelp = true, description = "display this help message")
    private boolean helpRequested = false;

    @Option(names = {"-p", "--path"},
        description = "the path representing the URI.\n  [default: ${DEFAULT-VALUE}]")
    private String path = "/zenoh/examples/java/put/hello";

    @Option(names = {"-l", "--locator"},
        description = "The locator to be used to boostrap the zenoh session. By default dynamic discovery is used")
    private String locator = null;

    @Override
    public void run() {
        try {
            Path p = new Path(path);

            System.out.println("Login to Zenoh (locator=" + locator + ")...");
            Zenoh z = Zenoh.login(locator);

            Workspace w = z.workspace();

            System.out.println("Remove " + p);
            w.remove(p);

            z.logout();
        } catch (Throwable e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        int exitCode = new CommandLine(new ZRemove()).execute(args);
        System.exit(exitCode);
    }
}