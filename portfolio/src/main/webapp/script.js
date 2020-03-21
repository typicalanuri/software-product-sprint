// Copyright 2019 Google LLC
//
// Licensed under the Apache License, Version 2.0 (the "License");
// you may not use this file except in compliance with the License.
// You may obtain a copy of the License at
//
//     https://www.apache.org/licenses/LICENSE-2.0
//
// Unless required by applicable law or agreed to in writing, software
// distributed under the License is distributed on an "AS IS" BASIS,
// WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
// See the License for the specific language governing permissions and
// limitations under the License.

/**
 * Adds a random greeting to the page.
 */
function addRandomGreeting() {
  const greetings =
      ['Chicken and Rice', 'Salmon and veggies', 'Cookies!', 'Rice Cakes'];

  // Pick a random greeting.
  const greeting = greetings[Math.floor(Math.random() * greetings.length)];

  // Add it to the page.
  const greetingContainer = document.getElementById('greeting-container');
  greetingContainer.innerText = greeting;
}
function getdataservlet() {
  console.log('Fetching data servlet.');

  // The fetch() function returns a Promise because the request is asynchronous.
  const responsePromise = fetch('/data');

  // When the request is complete, pass the response into handleResponse().
  responsePromise.then(handleResponse);
}
function getComments(){
    const responsePromise = fetch('/data');
    responsePromise.then(handleResponse);
}
function handleResponse(response) {
  console.log('Handling the response.');
  debugger;
  console.log(response);
  // response.text() returns a Promise, because the response is a stream of
  // content and not a simple variable.
  const textPromise = response.text();

  // When the response is converted to text, pass the result into the
  // addQuoteToDom() function.
  textPromise.then(addServletToDom);
}

function addServletToDom(servlet) {
  console.log('Adding data to dom: ' + servlet);

  const quoteContainer = document.getElementById('data-container');
  quoteContainer.innerText = servlet;
}

function getRandomQuoteUsingArrowFunctions() {
  fetch('/data').then(response => response.text()).then((response) => {
    document.getElementById('response-container').innerText = response;
  });
}

async function getRandomQuoteUsingAsyncAwait() {
  const response = await fetch('/random-quote');
  const quote = await response.text();
  document.getElementById('quote-container').innerText = quote;
}