/*
 * Copyright (c) 2019 ChainFront LLC.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *    http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package pcrypto.cf.common.api.controller

import org.springframework.http.HttpHeaders
import org.springframework.http.HttpStatus
import org.springframework.http.MediaType
import org.springframework.http.ResponseEntity
import org.springframework.stereotype.Controller
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.ResponseBody
import org.springframework.web.bind.annotation.ResponseStatus
import pcrypto.cf.exception.ApiError
import springfox.documentation.annotations.ApiIgnore


/**
 * Handler for returning custom XML, JSON, or HTML responses to 404 (Not Found) situations. This is a separate
 * controller (rather than part of ControllerExceptionHandler) as the 404 is generated prior to
 * Spring MVC exception handling.
 */
@ApiIgnore
@Controller
@RequestMapping(value = ["/resourceNotFound"], produces = [MediaType.APPLICATION_JSON_VALUE, MediaType.TEXT_HTML_VALUE])
class ResourceNotFoundController {

    @RequestMapping(produces = [MediaType.APPLICATION_JSON_VALUE])
    @ResponseBody
    fun handleResourceNotFound(): ResponseEntity<ApiError> {
        val apiError = ApiError()
        apiError.httpStatusCode = HttpStatus.NOT_FOUND.value()
        apiError.httpMessage = HttpStatus.NOT_FOUND.reasonPhrase
        return ResponseEntity(apiError, HttpHeaders(), HttpStatus.NOT_FOUND)
    }


    @RequestMapping(produces = [MediaType.TEXT_HTML_VALUE])
    @ResponseStatus(HttpStatus.NOT_FOUND)
    fun handleResourceNotFoundHtml(): String {
        return "error/404"
    }

}
