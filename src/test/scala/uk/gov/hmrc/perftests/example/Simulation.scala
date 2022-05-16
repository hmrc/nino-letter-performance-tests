/*
 * Copyright 2022 HM Revenue & Customs
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

package uk.gov.hmrc.perftests.example

import uk.gov.hmrc.performance.simulation.PerformanceTestRunner
import uk.gov.hmrc.perftests.example.Requests._

class Simulation extends PerformanceTestRunner {

  setup("home-page", "Home Page") withRequests navigateToHomePage

  setup("what-is-your-name-page", "Get/Post what is your name") withRequests (
    getWhatIsYourNamePage,
    postWhatIsYourNamePage
    )

  setup("previous-name-page", "Get/Post previous name page") withRequests (
    getPreviousNamePage,
    postPreviousNamePage
    )

  setup("date-of-birth-page", "Get/Post date of birth page") withRequests (
    getDOBPage,
    postDOBPage
    )

  setup("what-is-your-gender-page", "Get/Post what is your gender page") withRequests (
    getGenderPage,
    postGenderPage
    )

  setup("current-address-uk-page", "Get/Post current address uk page") withRequests (
    getCurrentAddressUKPage,
    postCurrentAddressUKPage
    )

  setup("current-address-page", "Get/Post current address page") withRequests (
    getCurrentAddressPage,
    postCurrentAddressPage
    )

  setup("previous-address-page", "Get/Post previous address page") withRequests (
    getPreviousAddressPage,
    postPreviousAddressPage
    )
  setup("living-abroad-page", "Get/Post living abroad page") withRequests (
    getLivingAbroadPage,
    postLivingAbroadPage
    )

  setup("telephone-number-page", "Get/Post telephone number page") withRequests (
    getTelephoneNumberPage,
    postTelephoneNumberPage
    )

  setup("nino-page", "Get/Post nino page") withRequests (
    getNinoPage,
    postNinoPage
    )

  setup("married-page", "Get/Post married page") withRequests (
    getMarriedPage,
    postMarriedPage
    )

  setup("marriage-civil-partnership-page", "Get/Post marriage civil partnership page") withRequests (
    getMarriageCivilPartnershipPage,
    postMarriageCivilPartnershipPage
    )

  setup("child-benefit-page", "Get/Post child benefit page") withRequests (
    getChildBenefitPage,
    postChildBenefitPage
    )

  setup("other-benefits-page", "Get/Post other benefits page") withRequests (
    getOtherBenefitsPage,
    postOtherBenefitsPage
    )

  setup("worked-in-uk-page", "Get/Post worked in uk page") withRequests (
    getWorkedInUKPage,
    postWorkedInUKPage
    )

  setup("doc-page", "Get/Post doc page") withRequests (
    getDocPage,
    postDocPage
    )

  setup("which-doc-page", "Get/Post which doc page") withRequests (
    getWhichDocPage,
    postWhichDocPage
    )

  setup("submit-generate", "Get/Post check your answers and generate pages") withRequests (
    getCheckAnswersPage,
    getNextStepPage,
    getPrintFormPage
    )

  runSimulation()
}
