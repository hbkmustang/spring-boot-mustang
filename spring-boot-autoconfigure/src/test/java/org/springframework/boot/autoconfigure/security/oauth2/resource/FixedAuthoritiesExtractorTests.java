/*
 * Copyright 2012-2015 the original author or authors.
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      https://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package org.springframework.boot.autoconfigure.security.oauth2.resource;

import java.util.Arrays;
import java.util.LinkedHashMap;
import java.util.Map;

import org.junit.Test;

import static org.junit.Assert.assertEquals;

/**
 * Tests for {@link FixedAuthoritiesExtractor}.
 *
 * @author Dave Syer
 */
public class FixedAuthoritiesExtractorTests {

	private FixedAuthoritiesExtractor extractor = new FixedAuthoritiesExtractor();

	private Map<String, Object> map = new LinkedHashMap<String, Object>();

	@Test
	public void authorities() {
		this.map.put("authorities", "ROLE_ADMIN");
		assertEquals("[ROLE_ADMIN]",
				this.extractor.extractAuthorities(this.map).toString());
	}

	@Test
	public void authoritiesCommaSeparated() {
		this.map.put("authorities", "ROLE_USER,ROLE_ADMIN");
		assertEquals("[ROLE_USER, ROLE_ADMIN]",
				this.extractor.extractAuthorities(this.map).toString());
	}

	@Test
	public void authoritiesArray() {
		this.map.put("authorities", new String[] { "ROLE_USER", "ROLE_ADMIN" });
		assertEquals("[ROLE_USER, ROLE_ADMIN]",
				this.extractor.extractAuthorities(this.map).toString());
	}

	@Test
	public void authoritiesList() {
		this.map.put("authorities", Arrays.asList("ROLE_USER", "ROLE_ADMIN"));
		assertEquals("[ROLE_USER, ROLE_ADMIN]",
				this.extractor.extractAuthorities(this.map).toString());
	}

}