/*
 * Copyright 2022 Scala Native Polling Execution Context Scheduler Working Group
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.armanbilge.snpecs

import scala.concurrent.ExecutionContext

trait PollingExecutionContextScheduler extends Polling with ExecutionContext with Scheduler

object PollingExecutionContextScheduler {

  private[this] var _global: PollingExecutionContextScheduler = null

  /** Install a [[PollingExecutionContextScheduler]] as the [[global]]. You should do this as early
    * as possible in your application, before calling anything that may need it (i.e. JDK NIO APIs).
    *
    * The first time this method is called, the given [[PollingExecutionContextScheduler]] will be
    * installed and it will return `true`. All subsequent invocations will no-op and return `false`.
    *
    * This method should be called ''exactly'' once in an application. A library should never call
    * this method.
    */
  def installGlobal(global: => PollingExecutionContextScheduler): Boolean =
    if (_global == null) {
      _global = global
      true
    } else {
      false
    }

  /** '''DO NOT USE THIS!!!''' Instead you should request a [[PollingExecutionContextScheduler]] as
    * an explicit or implicit parameter of your API.
    *
    * There is exactly one situation to explicitly rely on the [[global]]: you are implementing JDK
    * APIs, in which case it would be impossible to obtain a [[PollingExecutionContextScheduler]] by
    * any other means.
    *
    * Otherwise, '''STAY AWAY!!!'''
    */
  def global: PollingExecutionContextScheduler = _global

}
