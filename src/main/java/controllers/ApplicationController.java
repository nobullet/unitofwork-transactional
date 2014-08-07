/**
 * Copyright (C) 2013 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License"); you may not use this file except in compliance with
 * the License. You may obtain a copy of the License at
 *
 * http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software distributed under the License is distributed on
 * an "AS IS" BASIS, WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied. See the License for the
 * specific language governing permissions and limitations under the License.
 */
package controllers;

import com.google.inject.Singleton;
import javax.inject.Inject;
import ninja.Result;
import ninja.Results;
import service.UserService;

@Singleton
public class ApplicationController {

    @Inject
    UserService userService;

    // Unit of Work.
    public Result userUOW() {
        return noCacheHeaders(Results.json()).render(userService.getUserOneUOW());
    }

    // Transactional.
    public Result userT() {
        return noCacheHeaders(Results.json()).render(userService.getUserOneT());
    }

    // Not Transactional nor UnitOfWork.
    public Result userNTNUOW() {
        return noCacheHeaders(Results.json()).render(userService.getUserOneNTNUOW());
    }

    // Add no cache headers.
    static Result noCacheHeaders(Result result) {
        return result.addHeader("Cache-Control", "no-cache, must-revalidate")
                .addHeader("Pragma", "no-cache")
                .addHeader("Expires", "Sat, 26 Jul 1997 05:00:00 GMT");
    }
}
